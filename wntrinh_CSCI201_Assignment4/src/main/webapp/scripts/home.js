var openedMap = false;
function initMap() {
	if (openedMap == false) {
		openedMap = true;
		var map = new google.maps.Map(document.getElementById('map'), {
	        center: {lat: 34.02116, lng: -118.287132}, // You can set this to any default location
	        zoom: 4
	    });
    
	    const latLng = {lat: 34.02116, lng: -118.287132};
	    // Set a default marker
	    var marker = new google.maps.Marker({
	        position: latLng,
	        map: map
	    });
	    lastMarker = marker;
	} else {
		var map = new google.maps.Map(document.getElementById('map'), {
	        center: {lat: lastMarker.lat(), lng: lastMarker.lng()}, // You can set this to any default location
	        zoom: 4
	    });
	    
	    const latLng = {lat: lastMarker.lat(), lng: lastMarker.lng()};
	    // Set a default marker
	    var marker = new google.maps.Marker({
	        position: latLng,
	        map: map
	    });
	    lastMarker = marker;
	}

    map.addListener('click', function(e) {
        placeMarkerAndPanTo(e.latLng, map);
    });
}

var lastMarker = null;
function placeMarkerAndPanTo(latLng, map) {
	
	// Remove the last marker
	if (lastMarker != null) {
	    lastMarker.setMap(null);
	}
	
	// Set a new marker
    var marker = new google.maps.Marker({
        position: latLng,
        map: map
    });
    
    // Pan to new location in map
    map.panTo(latLng);
    lastMarker = marker;
    document.getElementById('latitude').value = latLng.lat();
    document.getElementById('longitude').value = latLng.lng();
}

// Create a function to open the map overlay
function openMap() {
    var mapOverlay = document.getElementById('map-overlay');
    mapOverlay.style.display = "flex"; // Use flex to center the map
    var mapsBtn = document.getElementsByClassName('googleMaps');
    for (var i = 0; i < mapsBtn.length; ++i) {
	    mapsBtn[i].style.color = 'black'; // Set the text color to white
	    mapsBtn[i].style.backgroundColor = 'white'; // Set the background color to grey (optional)
	}
    initMap(); // Initialize the map if needed
}

// Create a function to close the map overlay
function closeMap() {
    var mapOverlay = document.getElementById('map-overlay');
    mapOverlay.style.display = "none";
    var mapsBtn = document.getElementsByClassName('googleMaps');
    for (var i = 0; i < mapsBtn.length; ++i) {
	    mapsBtn[i].style.color = 'white'; // Set the text color to white
	    mapsBtn[i].style.backgroundColor = '#007bff'; // Set the background color to grey (optional)
	}
}

// Method to obtain a cookie
function getCookie(name) {
    let cookieArray = document.cookie.split('; ');
    let cookie = cookieArray.find(row => row.startsWith(name + '='));
    return cookie ? cookie.split('=')[1] : null;
}

// Method to log the current user out
function logUserOut() {
    document.cookie = "email=; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/;";
    console.log("Logged current user out.");
}

// Add an event listener to the map overlay to close it when the background is clicked
document.getElementById('map-overlay').addEventListener('click', function(event) {
    // Check if the clicked element is the overlay itself and not its children
    if (event.target === document.getElementById('map-overlay')) {
        closeMap();
    }
});

// Update the event listener for the button
document.querySelector('.googleMaps').addEventListener('click', function(event) {
    event.preventDefault(); // Prevent the form from submitting
    openMap(); // Call your function to show the map
});

function parseXML(xmlString) {
	console.log(xmlString);
    const parser = new DOMParser();
    const xmlDoc = parser.parseFromString(xmlString, "text/xml");
    const restaurantList = xmlDoc.getElementsByTagName("Restaurant");
    const restaurants = [];

    for (var i = 0; i < restaurantList.length; ++i) {
		console.log("Length: " + restaurantList.length);
        const restaurant = restaurantList[i];
        console.log("XML name at index " + i + " is: " + restaurant.getElementsByTagName("Name")[0].textContent);
        const name = restaurant.getElementsByTagName("Name")[0].textContent;
        const displayPhone = restaurant.getElementsByTagName("DisplayPhone")[0].textContent;
        const price = restaurant.getElementsByTagName("Price")[0].textContent;
        const rating = restaurant.getElementsByTagName("Rating")[0].textContent;
        const address = restaurant.getElementsByTagName("Address")[0].textContent;
        const cuisine = restaurant.getElementsByTagName("Cuisine")[0].textContent;
        const url = restaurant.getElementsByTagName("Url")[0].textContent;
        const imageUrl = restaurant.getElementsByTagName("ImageUrl")[0].textContent;
        
        if (!name || !displayPhone || !price || !rating || !address || !cuisine || !url || !imageUrl) {
            console.log("Skipping a restaurant due to missing data.");
            continue;
        }
        
        // Create an object with the parsed data
        const restaurantData = {
            name: name,
            displayPhone: displayPhone,
            price: price,
            rating: rating,
            address: address,
            cuisine: cuisine,
            url: url,
            imageUrl: imageUrl,
        };

        restaurants.push(restaurantData);
    }

    return restaurants;
}

var clicked = 0;

function submitForm() {
	
	const lat = document.getElementById("latitude");
	const long = document.getElementById("longitude");
	const restName = document.getElementById("restaurant-name");
	
	console.log("Called submitForm()!");
	
	if (lat.value.trim() === "" || isNaN(lat.value) || long.value.trim() === "" || isNaN(long.value)) {
        // Display an error message or perform any other desired action
        checkInputs(true, "error-msg");
        return false; // Prevent form submission
    } else if (restName.value == "") {
		checkInputs(true, "search-msg");
		return false;
	} else {
		checkInputs(false, "search-msg");
		checkInputs(false, "error-msg");
	}
    
    checkInputs(false, "error-msg");
	
	var xhr = new XMLHttpRequest();
	var form = document.getElementById("searchForm");
    
    // FOR OTHER USERS: update relative path below
    // "/(name of root folder)/processSignUp?"
    xhr.open("post", "/wntrinh_CSCI201_Assignment4/processSearch?" + new URLSearchParams(new FormData(form)).toString(), true);
    
    console.log("Link: " + "/wntrinh_CSCI201_Assignment4/processSearch?" + new URLSearchParams(new FormData(form)).toString());
    xhr.onreadystatechange = function() {
		if (xhr.readyState == XMLHttpRequest.DONE) {
			var status = xhr.status;
			if (status == 0 || (status >= 200 && status < 400)) {
	            // var response = xhr.responseText;
				// const parsedData = parseXML(response);
				// console.log("name: " + parsedData[0].name);
				// console.log("address: " + parsedData[0].address);
				// console.log("rating: " + parsedData[0].rating);
				const jsonObject = JSON.parse(xhr.responseText);
				const searchResults = jsonObject.businesses;
				const size = searchResults.length;
                if (size == 0) {
                    checkInputs(true, "null-msg");
                    return false;
                }

                checkInputs(false, "null-msg");
                
                var searchTerms = document.getElementById("restaurant-name").value;                
                var message = "Results for \"" + searchTerms + "\"";

                // Reuse null message div to serve as results message
                // DEBUG: Might need to reset...
                var resultsMessage = document.getElementById("null-msg");
                resultsMessage.style.display = "block";
                resultsMessage.style.color = "grey";
                resultsMessage.style.fontSize = "24px";
                resultsMessage.style.fontWeight = "normal";
                resultsMessage.style.marginTop = "1em";
                resultsMessage.innerHTML = message;
                
                // Hide the image section
                document.getElementById("featured").style.display = "none";
                
                var resultsContainer = document.getElementById("results");
                
                while (resultsContainer.firstChild) {
				    resultsContainer.removeChild(resultsContainer.firstChild);
				}
                
                
                for (var i = 0; i < size; ++i) {
				    const restaurant = searchResults[i];
				    const add = restaurant.location.display_address;
				    var dispAddress = "";
				    for (var j = 0; j < add.length; ++j) {
				        dispAddress += (j < add.length - 1) ? add[j] + " " : add[j];
				    }
				
				    // Create a container div for each restaurant
				    var restaurantContainer = document.createElement("div");
				    restaurantContainer.className = "restaurant-container";
				
				    // Create an image element
				    var imageElement = document.createElement("img");
				    imageElement.src = restaurant.image_url;
				    imageElement.alt = "Restaurant Image";
				    imageElement.style.cursor = "pointer";
				    
				    var imageContainer = document.createElement("div");
    				imageContainer.className = "image-container";
    				imageContainer.appendChild(imageElement);
				    
				    var restaurantInfo = document.createElement("div");
    				restaurantInfo.className = "restaurant-data";
				
				    // Create elements for restaurant details
				    var nameElement = document.createElement("p");
				    nameElement.textContent = restaurant.name;
				    nameElement.style.fontSize = "22px";
				
				    var addressElement = document.createElement("p");
				    addressElement.textContent = dispAddress;
				
				    var urlElement = document.createElement("p");
				    var urlLink = document.createElement("a");
				    // Strip url of any extra characters
				    let url = restaurant.url;
				    let shortUrl = url.split('?')[0];
				    urlLink.href = shortUrl;
				    urlLink.textContent = shortUrl;
				    urlLink.style.color = '#0073bb';
				    
				    urlElement.appendChild(urlLink);
				    
				    restaurantInfo.appendChild(nameElement);
				    restaurantInfo.appendChild(addressElement);
				    restaurantInfo.appendChild(urlElement);
				
				    // Append all elements to the restaurant container
				    restaurantContainer.appendChild(imageContainer);
				    restaurantContainer.appendChild(restaurantInfo);
				
				    // Append the restaurant container to the "results" div
				    resultsContainer.appendChild(restaurantContainer);
				    
				    imageElement.addEventListener("click", function() {
						// Remove every other element
					    while (resultsContainer.firstChild) {
						    resultsContainer.removeChild(resultsContainer.firstChild);
						}
						resultsContainer.appendChild(showDetails(restaurant));
						insertRestaurant(restaurant);
						
						var favoriteButton = document.createElement("button");
						favoriteButton.id = "favorite";
						favoriteButton.textContent = "★Add to Favorites";
						favoriteButton.style.display = "none";
						
						var removeFavButton = document.createElement("button");
						removeFavButton.id = "removeFavorite";
						removeFavButton.textContent = "Remove from Favorites";
						removeFavButton.style.display = "none";
						
						var reserveButton = document.createElement("button");
						reserveButton.id = "reserve";
						reserveButton.style.display = "block";
						reserveButton.textContent = "Add Reservation";
						reserveButton.formAction = "";
						
						const userEmail = getCookie("email");
						if (userEmail) {
						    isFavorite(restaurant.id, function(result) {
						        function addToFavoritesHandler(event) {
						            addToFavorites(event, restaurant.id, favoriteButton, removeFavButton);
						        }
						
						        function removeFromFavoritesHandler(event) {
						            removeFromFavorites(event, restaurant.id, favoriteButton, removeFavButton);
						        }
						        
						        function addToReservationsHandler(event, restaurant_id, date_input, time_input, notes_input) {
						            addToReservations(event, restaurant_id, date_input, time_input, notes_input);
						        }
						        
						        favoriteButton.addEventListener("click", addToFavoritesHandler);
						        removeFavButton.addEventListener("click", removeFromFavoritesHandler);
						
						        if (result == 0) {
						            favoriteButton.style.display = "block";
						        } else if (result == 1) {
						            removeFavButton.style.display = "block";
						        }
						
						        resultsContainer.appendChild(favoriteButton);
						        resultsContainer.appendChild(removeFavButton);
						        resultsContainer.appendChild(reserveButton);
						
						        var reservationFormDiv = document.createElement("div");
						        reservationFormDiv.id = "reservation-form";
						
						        var form = document.createElement("form");
						        form.id = "reservationForm";
						
						        var dateTimeContainer = document.createElement("div");
						        dateTimeContainer.style.display = "flex";
						
						        var dateInput = document.createElement("input");
						        dateInput.type = "date";
						        dateInput.id = "reservationDate";
						        dateInput.placeholder = "Date";
						
						        var timeInput = document.createElement("input");
						        timeInput.type = "time";
						        timeInput.id = "reservationTime";
						        timeInput.placeholder = "Time";
						
						        dateTimeContainer.appendChild(dateInput);
						        dateTimeContainer.appendChild(timeInput);
						
						        var notesInput = document.createElement("textarea");
						        notesInput.type = "text";
						        notesInput.id = "reservationNotes";
						        notesInput.placeholder = "Reservation Notes";
						
						        form.appendChild(dateTimeContainer);
						        form.appendChild(notesInput);
						        
						        var errorMsg = document.createElement("p");
				    			errorMsg.textContent = "Missing date or time.";
							    errorMsg.id = "missing-time-date";
							    errorMsg.style.color = '#C00000';
							    errorMsg.style.display = "none";
							    
							    form.appendChild(errorMsg);
						
						        var submitButton = document.createElement("button");
						        submitButton.type = "button";
						        submitButton.id = "reserve";
						        submitButton.textContent = "Submit Reservation";
						        submitButton.addEventListener("click", function(event) {
									console.log(dateInput.value + " and " + timeInput.value);
									if (dateInput.value != "" && timeInput.value != "") {
									    addToReservationsHandler(event, restaurant.id, dateInput.value, timeInput.value, notesInput.value);
									    dateInput.value = "";
									    timeInput.value = "";
									    notesInput.value = "";
									    errorMsg.style.display = "none";
								    } else {
										errorMsg.style.display = "block";
									}
								});
							    form.appendChild(submitButton);
						
						        reservationFormDiv.appendChild(form);
						        resultsContainer.appendChild(reservationFormDiv);
						        reservationFormDiv.style.display = "none";
						
						        clicked = 0;
						        reserveButton.addEventListener("click", function(event) {
									event.preventDefault();
						            ++clicked;
						            if (clicked % 2 == 1) {
						                reservationFormDiv.style.display = "block";
						            } else {
						                reservationFormDiv.style.display = "none";
						            }
						        });
						    });
						}
					});
				    
				    /*console.log("");
				    console.log("Restaurant data:");
				    console.log("name: " + restaurant.name);
				    console.log("phone: " + restaurant.display_phone);
				    console.log("price: " + restaurant.price);
				    console.log("rating: " + restaurant.rating);
				    console.log("url: " + restaurant.url);
				    console.log("image_url: " + restaurant.image_url);*/
				}				
	        } else {
				 console.log("Error: " + status + " code.")
			}
		}
    };
    xhr.send();
    return false;
}

function showDetails(restaurant) {
	var resultsMessage = document.getElementById("null-msg");
	resultsMessage.innerHTML = restaurant.name;
	
	const add = restaurant.location.display_address;
    var dispAddress = "";
    for (var j = 0; j < add.length; ++j) {
        dispAddress += (j < add.length - 1) ? add[j] + " " : add[j];
    }
	// Create a container div for each restaurant
    var restaurantContainer = document.createElement("div");
    restaurantContainer.className = "restaurant-container";

    // Create an image element
    var imageElement = document.createElement("img");
    imageElement.src = restaurant.image_url;
    imageElement.alt = "Restaurant Image";
    imageElement.style.cursor = "pointer";
    
    var imageLink = document.createElement("a");
    // Strip url of any extra characters
    let url = restaurant.url;
    let shortUrl = url.split('?')[0];
	imageLink.href = shortUrl;
	imageLink.appendChild(imageElement);
    
    var imageContainer = document.createElement("div");
	imageContainer.className = "image-container";
	imageContainer.appendChild(imageLink);
    
    var restaurantInfo = document.createElement("div");
	restaurantInfo.className = "restaurant-data";

    // Create elements for restaurant details
    var addressElement = document.createElement("p");
    addressElement.textContent = "Address: " + dispAddress;
    restaurantInfo.appendChild(addressElement);
    
    var phoneNumElement = document.createElement("p");
    phoneNumElement.textContent = "Phone No. " + restaurant.display_phone;
    restaurantInfo.appendChild(phoneNumElement);
    
    var cuisineElement = document.createElement("p");
    cuisineElement.textContent = "Cuisine: " + restaurant.categories[0].title;
    restaurantInfo.appendChild(cuisineElement);
    
    var priceElement = document.createElement("p");
    priceElement.textContent = "Price: " + restaurant.price;
    restaurantInfo.appendChild(priceElement);
    
    
    var priceElement = document.createElement("p");
    var halfStar = '\u2BEA';
    priceElement.textContent = "Rating: ";
    var stars = document.createElement("div");
    stars.className = "star-rating";
    stars.innerHTML = '';
    for (var i = 1; i <= 5; ++i) {
        var star = document.createElement("span");
        if (i <= restaurant.rating) {
	        // Full star for each whole number in the rating
	        star.className = "star full-star";
	    } else if (i === Math.ceil(restaurant.rating) && restaurant.rating % 1 !== 0) {
	        // Half star for the fraction part (if any)
	        star.className = "star half-star";
	    } else {
	        // Empty stars for the remaining
	        star.className = "star empty-star";
	    }
        
        stars.appendChild(star);
    }
    priceElement.appendChild(stars);
    restaurantInfo.appendChild(priceElement);

    // Append all elements to the restaurant container
    restaurantContainer.appendChild(imageContainer);
    restaurantContainer.appendChild(restaurantInfo);
    return restaurantContainer;
}

function insertRestaurant(restaurant) {
    // Perform asynchronous request to add the restaurant to database
    var xhr = new XMLHttpRequest();
    
    console.log("Inside insertRestaurant(restaurant)");
    const add = restaurant.location.display_address;
    var dispAddress = "";
    for (var j = 0; j < add.length; ++j) {
        dispAddress += (j < add.length - 1) ? add[j] + " " : add[j];
    }
    let url = restaurant.url;
    let shortUrl = url.split('?')[0];
    var link = "/wntrinh_CSCI201_Assignment4/addRestaurant?restaurant-id=" + restaurant.id + "&name=" + restaurant.name + "&address=" + dispAddress + "&phone=" + restaurant.display_phone + "&cuisine=" + restaurant.categories[0].title + "&price=" + restaurant.price + "&rating=" + restaurant.rating + "&url=" + shortUrl + "&imageUrl=" + restaurant.image_url;

    // FOR OTHER USERS: Update the relative path below
    // "/(name of root folder)/addFavorite?"
    xhr.open("post", link, true);
    xhr.onreadystatechange = function() {
        if (xhr.readyState == XMLHttpRequest.DONE) {
            var status = xhr.status;
            if (status == 0 || (status >= 200 && status < 400)) {
                console.log("Inserted favorites successfully...");
            } else {
                console.log("Error: " + status + " code.");
            }
        }
    };
    xhr.send();
}

function isFavorite(restaurant_id, callback) {
    var xhr = new XMLHttpRequest();

    // FOR OTHER USERS: Update the relative path below
    // "/(name of root folder)/checkFavorite?"    
    // FOR OTHER USERS: update relative path below
    // "/(name of root folder)/processSignUp?"
    xhr.open("post", "/wntrinh_CSCI201_Assignment4/checkFavorite?restaurant-id=" + restaurant_id, true);
    xhr.onreadystatechange = function() {
        if (xhr.readyState == XMLHttpRequest.DONE) {
            var status = xhr.status;
            if (status == 0 || (status >= 200 && status < 400)) {
                var response = xhr.responseText;
                if (response == "Send restaurant data back here.") {
                    console.log(response);
                    callback(1, 1); // The restaurant is a favorite
                } else if (response == "No favorites found.") {
                    console.log(response);
                    callback(0, 0); // The restaurant is not a favorite
                } else {
                    console.log("Unexpected response: " + response);
                    callback("Unexpected response", 2); // Unexpected response
                }
            } else {
                console.log("Error: " + status + " code.");
                callback("Request failed", 2); // Request failed
            }
        }
    };

    // Prepare and send the request
    xhr.send();
}

function addToReservations(event, restaurant_id, date_input, time_input, notes_input) {
    event.preventDefault();
    console.log("Adding to reservations...");

    // Perform asynchronous request to add the restaurant to favorites
    var xhr = new XMLHttpRequest();

    // FOR OTHER USERS: Update the relative path below
    // "/(name of root folder)/addReservation?"
    console.log(restaurant_id + " " + date_input + " "+ time_input + " " + notes_input);
    xhr.open("post", "/wntrinh_CSCI201_Assignment4/addReservation?restaurant-id=" + restaurant_id + "&reservationDate=" + date_input + "&reservationTime=" + time_input + "&reservationNotes=" + notes_input, true);
    xhr.onreadystatechange = function() {
        if (xhr.readyState == XMLHttpRequest.DONE) {
            var status = xhr.status;
            if (status == 0 || (status >= 200 && status < 400)) {
                console.log("Inserted reservation successfully...");
            } else {
                console.log("Error: " + status + " code.");
            }
        }
    };
    xhr.send();
}

function addToFavorites(event, restaurant_id, favoriteButton, removeFavButton) {
    event.preventDefault();
    console.log("Adding to favorites...");

    // Perform asynchronous request to add the restaurant to favorites
    var xhr = new XMLHttpRequest();

    // FOR OTHER USERS: Update the relative path below
    // "/(name of root folder)/addFavorite?"
    xhr.open("post", "/wntrinh_CSCI201_Assignment4/addFavorite?restaurant-id=" + restaurant_id, true);
    xhr.onreadystatechange = function() {
        if (xhr.readyState == XMLHttpRequest.DONE) {
            var status = xhr.status;
            if (status == 0 || (status >= 200 && status < 400)) {
                console.log("Inserted favorites successfully...");
            } else {
                console.log("Error: " + status + " code.");
            }
        }
    };
    xhr.send();
    favoriteButton.style.display = "none";
    removeFavButton.style.display = "block";
}

function removeFromFavorites(event, restaurant_id, favoriteButton, removeFavButton) {
    event.preventDefault();
    console.log("Removing from favorites...");
    
    // Perform asynchronous request to add the restaurant to favorites
    var xhr = new XMLHttpRequest();

    // FOR OTHER USERS: Update the relative path below
    // "/(name of root folder)/removeFavorite?"
    xhr.open("post", "/wntrinh_CSCI201_Assignment4/removeFavorite?restaurant-id=" + restaurant_id, true);
    xhr.onreadystatechange = function() {
        if (xhr.readyState == XMLHttpRequest.DONE) {
            var status = xhr.status;
            if (status == 0 || (status >= 200 && status < 400)) {
                console.log("Removed favorites successfully...");
            } else {
                console.log("Error: " + status + " code.");
            }
        }
    };
    xhr.send();
    favoriteButton.style.display = "block";
    removeFavButton.style.display = "none";
}

function checkInputs(cond, component) { 
	var msg = document.getElementById(component);   
	if (cond) {
    	msg.style.display = "block";
	} else {
    	msg.style.display = "none";
	}
}

document.addEventListener("DOMContentLoaded", function() {

  var w = document.documentElement.clientWidth;
  var googleMapsElements = document.getElementsByClassName("googleMaps");
  
  // Select necessary navbar elements
  const loginNav = document.getElementById('login');
  const favoritesNav = document.getElementById('favorites');
  const reservationsNav = document.getElementById('reservations');
  const logoutNav = document.getElementById('logout');
  
  // Find if there is a cookie with the key "email"
  const userEmail = getCookie("email");
  
  // If there is, a user is logged in, else show guest mode
  if (userEmail) {
	  loginNav.style.display = 'none';
	  favoritesNav.style.display = 'inline';
      reservationsNav.style.display = 'inline';
      logoutNav.style.display = 'inline';
  } else {
	  loginNav.style.display = 'inline';
      favoritesNav.style.display = 'none';
      reservationsNav.style.display = 'none';
      logoutNav.style.display = 'none';
  }

  function adjustFont() {
    w = document.documentElement.clientWidth;
    
    if (w <= 576) {
		for (var i = 0; i < googleMapsElements.length; ++i) {
			googleMapsElements[i].style.fontSize = "10px";
		}
	} else if (w <= 640) {
		for (var i = 0; i < googleMapsElements.length; ++i) {
			googleMapsElements[i].style.fontSize = "11px";
		}
	} else if (w <= 721) {
      for (var i = 0; i < googleMapsElements.length; ++i) {
        googleMapsElements[i].style.fontSize = "12px";
      }
    } else {
      for (var i = 0; i < googleMapsElements.length; ++i) {
        googleMapsElements[i].style.fontSize = "14px";
      }
    }
  }

  // Resize listener
  window.addEventListener("resize", adjustFont);
});