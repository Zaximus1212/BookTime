//Cookie
const cookieArr = document.cookie.split("=")
const userId = cookieArr[1];
console.log(userId)

//DOM Elements
const bookForm = document.getElementById("book-form")
const bookTitle = document.getElementById("book-title")
const bookAuthor = document.getElementById("book-author")
const completionForm = document.getElementById("completion-form")
const completionTimeForm = document.getElementById("time-completion")
const completionBookId = document.getElementById("book-id-completion")
//const completionUpdateForm = document.getElementById("completion-update-form")
//const completionUpdateId = document.getElementById("completion-update-id")
//const completionUpdateTime = document.getElementById("completion-update-time")


const bookContainer = document.getElementById("book-container")
const completionContainer = document.getElementById("completion-container")


//Modal Elements
let bookTitleModal = document.getElementById('book-title-modal')
let updateBookBtn = document.getElementById('update-book-button')

const headers = {
    'Content-Type': 'application/json'
}

const baseUrl = "http://localhost:8080/"

function handleLogout() {
    let c = document.cookie.split(";");
    for(let i in c){
        document.cookie = /^[^=]+/.exec(c[i])[0]+";expires=Thu, 01 Jan 1970 00:00:00 GMT"
    }
}   // functioning properly

const handleSubmitCompletion = async (e) => {
    e.preventDefault()
    let completionObj = {
        time: document.getElementById("time-completion").value
    }
    await addCompletion(completionObj);
    document.getElementById("time-completion").value = ''
}   // functioning properly

async function addCompletion(obj) {
    console.log(obj)
    const response = await fetch(`${baseUrl}completion/complete/${userId}/${completionBookId.value}`, {
        method: "POST",
        body: JSON.stringify(obj),
        headers: headers
    })
        .catch(err => console.error(err.message))
    if (response.status === 200) {
        console.log("success!")
    }
}   // functioning properly

const handleSubmitBook = async (e) => {
    e.preventDefault()
    let bookObj = {
        title: document.getElementById("book-title").value,
        author: document.getElementById("book-author").value
    }
    await addBook(bookObj);
    document.getElementById("book-title").value = ''
    document.getElementById("book-author").value = ''
}   // functioning properly

async function addBook(obj) {
    console.log(obj)
    const response = await fetch(`${baseUrl}book/add`, {
        method: "POST",
        body: JSON.stringify(obj),
        headers: headers
    })
        .catch(err => console.error(err.message))
    if (response.status === 200) {
        console.log("success!")
    }
}   // functioning properly

async function getBooks(userId) {
    await fetch(`${baseUrl}book/all`, {
        method: "GET",
        headers: headers
    })
        .then(response => response.json())
        .then(data => createBookCards(data))
        .catch(err => console.error(err))
}

async function getBookById(bookId){
    await fetch(baseUrl + "book/" + bookId, {
        method: "GET",
        headers: headers
    })
        .then(res => res.json())
        .then(data => populateModal(data))
        .catch(err => console.error(err.message))
}

async function handleBookEdit(bookId, bookTitle, bookAuthor){
    let bookObj = {
        id: bookId,
        title: bookTitle
//        author:
    }
    console.log(bookObj)
    await fetch(baseUrl + "book/update", {
        method: "PUT",
        body: JSON.stringify(bookObj),
        headers: headers
    })
        .catch(err => console.error(err))

    return getBooks(userId);
}

async function handleDelete(bookId){
    await fetch(baseUrl + "book/delete/" + bookId, {
        method: "DELETE",
        headers: headers
    })
        .catch(err => console.error(err))

    return getBooks(userId);
}

const createBookCards = (array) => {    //unfinished with changing to book and potentially duping for completion
    bookContainer.innerHTML = ''
    array.forEach(obj => {
        let bookCard = document.createElement("div")
        bookCard.classList.add("m-2")
        bookCard.innerHTML = `
            <div class="card d-flex" style="width: 18rem; height: 18rem;">
                <div class="card-body d-flex flex-column justify-content-between" style="height: available">
                    <p class="card-title">${obj.title}</p>
                    <p class="card-author">${obj.author}</p>
                    <div class="d-flex justify-content-between">
                        <button class="btn btn-danger" onclick="handleDelete(${obj.id})">Delete</button>
                        <button onclick="getBookById(${obj.id})" type="button" class="btn btn-primary"
                        data-bs-toggle="modal" data-bs-target="#book-edit-modal">
                        Edit
                        </button>
                    </div>
                </div>
            </div>
        `
        bookContainer.append(bookCard);
    })
}

const populateModal = (obj) =>{
    bookTitleModal.innerText = ''
    bookTitleModal.innerText = obj.title
    updateBookBtn.setAttribute('data-book-id', obj.id)
}

getBooks();   // functioning properly

completionForm.addEventListener("submit", handleSubmitCompletion)   // functioning properly
bookForm.addEventListener("submit", handleSubmitBook)   // functioning properly

updateBookBtn.addEventListener("click", (e)=>{
    let bookId = e.target.getAttribute('data-book-id')
    let bookAuthor = e.target.getAttribute('data-book-id')
    handleBookEdit(bookId, bookTitleModal.value);
})