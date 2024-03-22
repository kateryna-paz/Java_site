/**
 * Функція для перевірки форми створення/оновлення продукту.
 * Перевіряє, чи заповнені обов'язкові поля і валідні значення.
 * @returns {boolean} Повертає true, якщо форма пройшла валідацію, інакше - false.
 */
function validateForm() {
    let name = document.getElementById("name").value;
    let price = document.getElementById("price").value;
    let date = document.getElementById("date").value;

    if (name.trim() === '' || date.trim() === '') {
        alert("Поля 'Назва' або 'Дата випуску' не можуть бути порожніми");
        return false; // Зупинити відправку форми
    }
    if (price <= 0) {
        alert("Ціна повинна бути більшою за 0");
        return false; // Зупинити відправку форми
    }

    // Якщо всі поля пройшли перевірку, відправити форму
    document.getElementById("productForm").submit();
}

/**
 * Функція для перевірки поля "Назва" при пошуку.
 * Перевіряє, чи введено значення в поле "Назва".
 * @returns {boolean} Повертає true, якщо поле "Назва" не порожнє, інакше - false.
 */
function validateName() {
    let name = document.getElementById("name").value;

    if (name.trim() === '') {
        alert("Поле 'Назва' не може бути порожнім");
        return false; // Зупинити відправку форми
    }

    // Якщо всі поля пройшли перевірку, відправити форму
    document.getElementById("form").submit();
}

/**
 * Функція для пошуку продуктів за назвою фірми.
 * Відправляє запит на сервер і відображає результат у відповідній таблиці.
 */
function searchByFirm() {
    document.getElementById("groupSearchInput").value = '';
    let firmName = document.getElementById("firmSearchInput").value;
    fetch('/products/searchByFirm?firmName=' + firmName)
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json();
        })
        .then(data => updateTable(data))
        .catch(error => console.error('Fetch error:', error));
}

/**
 * Функція для пошуку продуктів за назвою групи.
 * Відправляє запит на сервер і відображає результат у відповідній таблиці.
 */
function searchByGroup() {
    document.getElementById("firmSearchInput").value = '';
    let groupName = document.getElementById("groupSearchInput").value;
    fetch('/products/searchByGroup?groupName=' + groupName)
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json();
        })
        .then(data => updateTable(data))
        .catch(error => console.error('Fetch error:', error));
}

/**
 * Функція для оновлення таблиці продуктів.
 * Відображає дані про продукти у таблиці або виводить повідомлення, якщо продукти не знайдено.
 * @param {Array} products Масив об'єктів продуктів для відображення у таблиці.
 */
function updateTable(products) {
    if (products === null || products.length === 0) {
        let tableBody = document.querySelector('#productsTable tbody');
        tableBody.innerHTML = '<tr><td colspan="8">Нажаль продукції за вашим запитом не знайдено</td></tr>';
    } else {
        let tableBody = document.querySelector('#productsTable tbody');
        tableBody.innerHTML = '';
        products.forEach(function(product) {
            let row = '<tr>' +
                '<td>' + product.id + '</td>' +
                '<td>' + product.name + '</td>' +
                '<td>' + product.productGroup.name + '</td>' +
                '<td>' + product.productFirm.name + '</td>' +
                '<td>' + product.description + '</td>' +
                '<td>' + new Date(product.date).toLocaleDateString('uk-UA', {day: "2-digit", month: "2-digit", year: "numeric"}) + '</td>' +
                '<td>' + product.price + '</td>' +
                '<td>' +
                '<a href="/products/' + product.id + '" class="button">' +
                '<i class="fas fa-pencil-alt"></i>' +
                '</a>' +
                '<a href="/products/delete/' + product.id + '" class="button icon">' +
                '<i class="fas fa-trash-alt"></i>' +
                '</a>' +
                '</td>' +
                '</tr>';
            tableBody.innerHTML += row;
        });
    }
}
