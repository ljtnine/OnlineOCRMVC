/**
 * Created by GrIfOn on 07.01.2017.
 */
/**
 * Created by GrIfOn on 28.12.2016.
 */
var OCR = function () {

    var newsJSON = {
        "first": {"newsTitle" : "first news", "newsContent" : "first content"},
        "second": {"newsTitle" : "second news", "newsContent" : "second content"},
        "third": {"newsTitle" : "third news", "newsContent" : "third content"},
        "fourth": {"newsTitle" : "fourth news", "newsContent" : "fourth content"},
        "fifth": {"newsTitle" : "fifth news", "newsContent" : "fifth content"},
        "sixth": {"newsTitle" : "sixth news", "newsContent" : "sixth content"}};

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

        form["submit"].addEventListener("click", function (event) {
            console.log(form["name"].value);
            console.log(form["lastname"].value);
            console.log(form["email"].value);
            console.log(form["message"].value);

            //form.submit();
        }, true);
    }

    prepareNewsFromJSON(newsJSON);
    assignNewsButtonsListeners();
    formValidationEvent();
}

window.onload = OCR;