window.addEventListener("load", loadImage)

let options = {
    name: [
        {
            isValid: function (domElement) {
                return (domElement["name"].value !== "");
            },
            message: "The name field is required. Please enter the name."
        },
        {
            isValid: function (domElement) {
                return domElement["name"].value.length > 2 && domElement["name"].value.length < 30;
            },
            message: "Username must be between 2 and 30 characters."
        },
        {
            isValid: function (domElement) {
                return (domElement["nameValidationResult"].value === "<Boolean>true</Boolean>");
            },
            message: "Username already exists."
        }
    ],
    password: [
        {
            isValid: function (domElement) {
                return (domElement["password"].value !== "");
            },
            message: "The password field is required. Please enter the password."
        },
        {
            isValid: function (domElement) {
                return domElement["password"].value.length > 5;
            },
            message: "Password must be over 5 characters."
        }
    ],
    confirmPassword: [
        {
            isValid: function (domElement) {
                return (domElement["confirmPassword"].value !== "");
            },
            message: "The confirm password field is required. Please enter the password."
        },
        {
            isValid: function (domElement) {
                return (domElement["password"].value === domElement["confirmPassword"].value);
            },
            message: "Password doesn't match"
        }
    ],
    email: [
        {
            isValid: function (domElement) {
                return (domElement["email"].value !== "");
            },
            message: "The email address field is required. Please enter the email."
        },
        {
            isValid: function (domElement) {
                const re = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
                return re.test(domElement["email"].value.toLowerCase());
            },
            message: "The email address is invalid. Please enter the correct email"
        }
    ],
    birthday: [
        {
            isValid: function (domElement) {
                return (domElement["birthday"].value !== "");
            },
            message: "The birthday field is required. Please enter the birthday date."
        },
        {
            isValid: function (domElement) {
                return (new Date(domElement["birthday"].value) < new Date());
            },
            message: "The birthday field is required. Please enter the birthday date."
        }
    ],
    gender: [
        {
            isValid: function (domElement) {
                return (domElement["gender"].value !== "");
            },
            message: "The gender field is required. Please enter the gender."
        }
    ],
    role: [
            {
            isValid: function (domElement) {
                return (domElement["gender"].value !== "");
            },
                message: "The role field is required. Please enter the role."
        }
    ],
    captcha: [
        {
            isValid: function (domElement) {
                return (domElement["captcha"].value !== "");
            },
            message: "The  captcha is required. Please enter the captcha."
        },
        {
            isValid: function (domElement) {
                return domElement["captchaValidationResult"].value === "<Boolean>true</Boolean>";
            },
            message: "Doesn't match."
        }
    ]
}

function validateAll(form, options) {
    isNewUsername(form).then(result => {
        validateCaptcha(result).then(resultCaptcha => {
            validate(resultCaptcha, options)
        })
    })
    return false;
}

function sendForm(form) {
    const xhr = new XMLHttpRequest();

    let urlEncodedData = ""
    let urlEncodedDataPairs = [];

    for(let i = 0; i < form.length; i++) {
        urlEncodedDataPairs.push( encodeURIComponent( form[i].name ) + '=' + encodeURIComponent( form[i].value ) );
    }
    urlEncodedData = urlEncodedDataPairs.join( '&' ).replace( /%20/g, '+' );
    xhr.addEventListener( "load", function(event) {
        alert( "Response loaded." );
    } );
    xhr.addEventListener( "error", function(event) {
        alert( "Something went wrong." );
    } );
    xhr.open( "POST", "/registration" );
    xhr.setRequestHeader( "Content-Type", "application/x-www-form-urlencoded" );
    xhr.send(urlEncodedData);
    xhr.onreadystatechange = function () {
        if (xhr.readyState !== 4) return;
        if (xhr.status !== 200) {
            return xhr.response;
        } else {
            location.href = xhr.responseURL;
            return xhr.response;
        }
    }
}

function validate(form, options) {
    let validationResult = true;
    for (let i = 0; i < form.length - 1; i++) {
        if (options[form[i].name] !== undefined && form[form[i].name].type !== "hidden") {
            for (let j = 0; j < options[form[i].name].length; j++) {
                let error = document.querySelector("." + form[i].name + "Error");
                if (!options[form[i].name][j].isValid(form)) {
                    error.innerHTML = options[form[i].name][j].message;
                    validationResult = false;
                    break;
                } else {
                    error.innerHTML = "";
                    error.className = form[i].name + "Error";
                }
            }
        }
    }
    if (validationResult === true) {
        sendForm(form);
    } else {
        loadImage();
    }
}

function isNewUsername(form) {

    return new Promise((resolve, reject) => {
        const xhr = new XMLHttpRequest();
        xhr.open("POST", "/api/nameValidation")
        xhr.send(form["name"].value);

        xhr.onreadystatechange = function () {
            if (xhr.readyState !== 4) return;
            if (xhr.status !== 200) {
                form["nameValidationResult"].value = false;
                resolve(form);
                return xhr.response;
            } else {
                form["nameValidationResult"].value = xhr.response;
                resolve(form);
                return xhr.response;
            }
        }
    })
}

function validateCaptcha(form) {

    return new Promise((resolve, reject) => {
        const xhr = new XMLHttpRequest();
        let captcha = form["captcha"].value;
        xhr.open("POST", "/api/captchaValidation");
        xhr.send(captcha);

        xhr.onreadystatechange = function () {
            if (xhr.readyState !== 4) return;

            if (xhr.status !== 200) {
                form["captchaValidationResult"].value = false;
                resolve(form);
                alert(xhr.status + ': ' + xhr.statusText);
            } else {
                form["captchaValidationResult"].value = xhr.response;
                resolve(form);
                return xhr.response;
            }
        }
    })
}

function imgLoad(url) {

    return new Promise(function (resolve, reject) {
        var xhr = new XMLHttpRequest();
        xhr.open('GET', url);
        xhr.responseType = 'blob';
        xhr.onreadystatechange = function () {
            if (xhr.readyState !== 4) return;
            if (xhr.status === 200) {
                resolve(xhr.response);
            } else {
                reject(new Error('Image didn\'t load successfully; error code:' + xhr.statusText));
            }
        };
        xhr.onerror = function () {
            reject(new Error('There was a network error.'));
        };
        xhr.send();
    });
}

function loadImage() {
    let image = document.images[0],
        myImage = new Image();
    myImage.crossOrigin = "";
    imgLoad('/captcha').then(function (response) {
        image.src = window.URL.createObjectURL(response);
    }, function (Error) {
        console.log(Error);
    });
}





