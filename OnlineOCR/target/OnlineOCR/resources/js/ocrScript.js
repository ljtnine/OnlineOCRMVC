/**
 * Created by GrIfOn on 28.12.2016.
 */

/**
 * The main module object
 * @type {{}}
 */
var OCR = {};

/**
 * Function for index page
 */
OCR.index = function () {

    var newsJSON = {
        "first": {"newsTitle" : "first news", "newsContent" : "first content"},
        "second": {"newsTitle" : "second news", "newsContent" : "second content"},
        "third": {"newsTitle" : "third news", "newsContent" : "third content"},
        "fourth": {"newsTitle" : "fourth news", "newsContent" : "fourth content"},
        "fifth": {"newsTitle" : "fifth news", "newsContent" : "fifth content"},
        "sixth": {"newsTitle" : "sixth news", "newsContent" : "sixth content"}};

    /**
     * Object for getting news from JSON objects.
     * @type {{getNews, putNews, getLength}}
     */
    var News = function() {
        var titles = [];
        var contents = [];

        return {
            getNews: function (index) {
                return {
                    title : titles[index % titles.length ],
                    content : contents[index % contents.length]
                };
            },
            putNews: function (news) {
                titles.push(news["newsTitle"]);
                contents.push(news["newsContent"]);
            },
            getLength: function() {
                return titles.length;
            }
        };
    }();

    function prepareNewsFromJSON(jsonString) {
        for (var object in jsonString) {
            News.putNews(jsonString[object]);
        }
    }

    function assignNewsButtonsListeners() {
        var leftBtn = document.getElementById("leftBtn");
        var rightBtn = document.getElementById("rightBtn");

        var newsTitle = document.getElementById("newsTitle");
        var newsContent = document.getElementById("newsContent");

        var index = 0;

        function setNews() {
            newsTitle.innerHTML = News.getNews(index)["title"];
            newsContent.innerHTML = News.getNews(index)["content"];
        }
        setNews();


        leftBtn.addEventListener("click", function (event) {
            index = index == 0 ? News.getLength() - 1 : index - 1;
            setNews();
        }, true);

        rightBtn.addEventListener("click", function (event) {
            index = index == News.getLength() - 1 ? 0 : index + 1;
            setNews();
        }, true);

        var interval = setInterval(function () {
            index = index == News.getLength() - 1 ? 0 : index + 1;
            setNews();
        }, 5000);
    }

    function formValidationEvent() {
        var form = document.getElementById("contact");
    }

    prepareNewsFromJSON(newsJSON);
    assignNewsButtonsListeners();
    formValidationEvent();
}

/**
 * Function for registration page
 */
OCR.registration = function () {
    var form = document.forms[0];
    var registration = form["register"];

    function invalid(elementId) {
        form[elementId].classList.remove("valid")
        form[elementId].classList.add("invalid");
    }

    function valid(elementId) {
        form[elementId].classList.add("valid")
        form[elementId].classList.remove("invalid");
    }

    function realTimeValidation (elementId) {
        var loginValidation = /^[^\s\W]{4,}$/g;
        var nameValidation = /^[^\W|\d]{3,}$/g;
        var mailValidation = /^[a-zA-Z0-9._]+?@[a-zA-Z0-9]+?[.][a-zA-Z0-9]{2,3}$/g;
        var passwordValidation = /[^\W]{8,}/g;

        if(elementId === "login") {
            if(!loginValidation.test(form["login"].value)) {
                invalid("login");
            } else {
                valid("login");
            }
        }

        if(elementId === "name") {
            if (!nameValidation.test(form["name"].value)) {
                invalid("name");
            } else {
                valid("name");
            }
        }

        if(elementId === "lastname") {
            if(!nameValidation.test(form["lastname"].value)) {
                invalid("lastname");
            } else {
                valid("lastname");
            }
        }

        if(elementId === "email") {
            if(!mailValidation.test(form["email"].value)) {
                invalid("email");
            } else {
                valid("email");
            }
        }

        if(elementId === "password" || elementId === "repeatPassword") {
            if(!passwordValidation.test(form["password"].value)) {
                invalid("password");
            } else {
                valid("password");
            }

            if(form["password"].value !== form["repeatPassword"].value) {
                invalid("repeatPassword");
            } else {
                valid("repeatPassword");
            }
        }

        if(elementId === "birthday") {
            if(new Date(form["birthday"].value) > new Date()) {
                invalid("birthday");
            } else {
                valid("birthday");
            }
        }
    }

    function inputListener(event) {
        if (event.target !== event.currentTarget) {
            realTimeValidation(event.target.id)
        }
        event.stopPropagation();
    }

    function formValidation () {
        var invalid = document.querySelectorAll(".invalid");
        if(invalid.length !== 0) {
            invalid[0].focus();
            return false;
        }
        var required = document.querySelectorAll("input[required]");
        for(var i = 0; i < required.length; ++i) {
            if(required[i].value.length === 0) {
                console.log(required[i].value);
                realTimeValidation(required[i].id);
                required[i].focus();
                return false;
            }
        }
        form.submit();
    }

    form.addEventListener("input", inputListener, false);
    registration.addEventListener("click", formValidation);
}

/**
 * Main function.
 * @returns depends on title of page. Return special function for each page
 */
OCR.main = function () {
    var title = document.title;
    if(title === "OCR") {
        return this.index;
    }
    if(title === "Registration") {
        return this.registration;
    }
}

window.onload = OCR.main();