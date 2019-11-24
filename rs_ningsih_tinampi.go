package main

import (
	"database/sql"
	"encoding/json"
	"log"
	"net/http"

	_ "github.com/mattn/go-sqlite3"
)

//Object
type responseObject struct {
	Response string
}

type userObject struct {
	Email    string
	Password string
}

//Function Helper
func initDatabase(database *sql.DB) *sql.Tx {
	tx, err2 := database.Begin()
	if err2 != nil {
		log.Println(err2)
	}

	stmt, err3 := tx.Prepare("CREATE TABLE IF NOT EXISTS userList (email TEXT PRIMARY KEY, password TEXT)")
	if err3 != nil {
		log.Println(err3)
	}
	stmt.Exec()
	defer stmt.Close()

	return tx

}

func main() {
	http.HandleFunc("/register", register)
	http.HandleFunc("/login", login)

	http.ListenAndServe(":8080", nil)
}

func register(w http.ResponseWriter, r *http.Request) {
	if r.Method != "POST" {
		http.Error(w, "404 not found.", http.StatusNotFound)
		return

	}

	if err0 := r.ParseForm(); err0 != nil {
		http.Error(w, "404 not found.", http.StatusNotFound)
		return
	}

	mEmail := r.FormValue("email")
	mPassword := r.FormValue("password")

	database, err1 := sql.Open("sqlite3", "./rs_ningsih_tinampi.db")
	if err1 != nil {
		log.Println(err1)
		return
	}
	tx := initDatabase(database)
	defer database.Close()
	defer tx.Commit()

	mEmailDatabase := ""
	isUserMatch := false
	rows, err2 := tx.Query("SELECT email FROM userList WHERE email=?", mEmail)
	if err2 != nil {
		log.Println(err2)
	}
	for rows.Next() {
		rows.Scan(&mEmailDatabase)
		isUserMatch = true
		break

	}

	if !isUserMatch {
		stmt, err2 := tx.Prepare("INSERT INTO userList (email, password) VALUES (?, ?)")
		if err2 != nil {
			log.Println(err2)
			return
		}
		stmt.Exec(mEmail, mPassword)
		defer stmt.Close()
	}

	w.Header().Set("Content-Type", "application/json")
	w.WriteHeader(200)

	m2 := responseObject{"Register success"}
	if isUserMatch {
		m2 = responseObject{"Register failed"}

	}
	a, err3 := json.Marshal(m2)
	if err3 != nil {
		log.Println(err3)
		return
	}
	w.Write(a)

}

func login(w http.ResponseWriter, r *http.Request) {
	if r.Method != "POST" {
		http.Error(w, "404 not found.", http.StatusNotFound)
		return

	}

	if err0 := r.ParseForm(); err0 != nil {
		http.Error(w, "404 not found.", http.StatusNotFound)
		return
	}

	mEmail := r.FormValue("email")
	mPassword := r.FormValue("password")

	database, err1 := sql.Open("sqlite3", "./rs_ningsih_tinampi.db")
	if err1 != nil {
		log.Println(err1)
	}
	tx := initDatabase(database)
	defer database.Close()
	defer tx.Commit()

	mEmailDatabase := ""
	mPasswordDatabase := ""
	isUserMatch := false
	rows, err2 := tx.Query("SELECT email, password FROM userList WHERE email=?", mEmail)
	if err2 != nil {
		log.Println(err2)
	}
	for rows.Next() {
		rows.Scan(&mEmailDatabase, &mPasswordDatabase)
		if (mEmail == mEmailDatabase) && (mPassword == mPasswordDatabase) {
			isUserMatch = true
			break

		}

	}

	w.Header().Set("Content-Type", "application/json")
	w.WriteHeader(200)

	m2 := responseObject{"Login success"}
	if !isUserMatch {
		m2 = responseObject{"Login failed"}
	}
	a, err3 := json.Marshal(m2)
	if err3 != nil {
		log.Println(err3)
		return
	}
	w.Write(a)

}
