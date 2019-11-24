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
		log.Println("Request method is not POST")
		http.Error(w, "Request method is wrong", http.StatusNotFound)
		return

	}

	if err0 := r.ParseMultipartForm(0); err0 != nil {
		log.Println("Post body is wrong")
		http.Error(w, "Post body is wrong", http.StatusNotFound)
		return
	}

	mEmail := r.FormValue("email")
	mPassword := r.FormValue("password")

	database, err1 := sql.Open("sqlite3", "./rs_ningsih_tinampi.db")
	if err1 != nil {
		log.Println(err1)
		http.Error(w, "Open database error", http.StatusInternalServerError)
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
		http.Error(w, "Read Database error", http.StatusInternalServerError)
		return
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
			http.Error(w, "Register user error", http.StatusInternalServerError)
			return
		}
		stmt.Exec(mEmail, mPassword)
		defer stmt.Close()
	}

	w.Header().Set("Content-Type", "application/json")
	w.WriteHeader(200)

	m2 := responseObject{"Register success"}
	if isUserMatch {
		log.Println("User already exists")
		http.Error(w, "User already exists", http.StatusConflict)
		return

	}
	a, err3 := json.Marshal(m2)
	if err3 != nil {
		log.Println(err3)
		http.Error(w, "Format response error", http.StatusInternalServerError)
		return
	}
	w.Write(a)

}

func login(w http.ResponseWriter, r *http.Request) {
	if r.Method != "POST" {
		log.Println("Request method is not POST")
		http.Error(w, "Request method is wrong", http.StatusNotFound)
		return

	}

	if err0 := r.ParseMultipartForm(0); err0 != nil {
		log.Println("Post body is wrong")
		http.Error(w, "Post body is wrong", http.StatusNotFound)
		return
	}

	mEmail := r.FormValue("email")
	mPassword := r.FormValue("password")

	database, err1 := sql.Open("sqlite3", "./rs_ningsih_tinampi.db")
	if err1 != nil {
		log.Println(err1)
		http.Error(w, "Open database error", http.StatusInternalServerError)
		return
	}
	tx := initDatabase(database)
	defer database.Close()
	defer tx.Commit()

	mEmailDatabase := ""
	mPasswordDatabase := ""
	isUserMatch := false
	log.Println(mEmail)
	rows, err2 := tx.Query("SELECT email, password FROM userList WHERE email=?", mEmail)
	if err2 != nil {
		log.Println(err2)
		http.Error(w, "Read Database error", http.StatusInternalServerError)
		return
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
		log.Println("User not found")
		http.Error(w, "User not found", http.StatusConflict)
		return
	}
	a, err3 := json.Marshal(m2)
	if err3 != nil {
		log.Println(err3)
		http.Error(w, "Format response error", http.StatusInternalServerError)
		return
	}
	w.Write(a)

}
