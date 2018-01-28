//represents the Location object being returned from the server
function Location(x, y) {
	this.x = x;
	this.y = y;
	this.locationID = this.x + " " + this.y;
}
var start;
var dest;

// create a chess table 8x8.
(function () {
    var square = "";
    var row = 0;
	while (row < 8) {
		var column = 0;
		while (column < 8) {
			var square = document.createElement("div");
			square.setAttribute("id", column + " " + row);
			square.setAttribute("row", row);
			square.setAttribute("column", column);
			square.setAttribute("onclick", 'spaceClicked(this.id);');
			if(row%2 === 0){
				square.className = (column%2 === 0)? "white" : "black";
			} else {
				square.className = (column%2 === 0)? "black" : "white";
			}

			if (column === 0){
				square.className += " clear";
			} 
			document.body.appendChild(square);
			column++
		}
		row++;
}
})();

//set the square red when clicked
function spaceClicked(id) {
	console.log(id);
	var square = document.getElementById(id);
	var squareClicked = new Location(square.getAttribute("column"), square.getAttribute("row"));

	if(!start) {
		start = squareClicked;
		changeColor(squareClicked, true);
	} else if (!dest){
		changeColor(squareClicked, true);
		dest = squareClicked;
		callApi($, start, dest);
	}

}

function changeColor(squareClicked, isRed) {
	var square = document.getElementById(squareClicked.locationID);
	if(isRed) {
		if (square.className.indexOf("clear") >= 0) {
			square.className = "red clear";
		} else {
			square.className = "red";
		}
	} else {
		if(square.getAttribute("row")%2 === 0){
				square.className = (square.getAttribute("column")%2 === 0)? "white" : "black";
			} else {
				square.className = (square.getAttribute("column")%2 === 0)? "black" : "white";
			}

			if (square.getAttribute("column") === 0){
				square.className += " clear";
			} 
	}
}

function callApi($, start, dest){
	console.log(start, dest);
	$(document).ready(function() {
		$.ajax({
			url: "http://localhost:8080/calculateMoves/x1="+start.x+"&y1="+start.y+"&x2="+dest.x+"&y2="+dest.y
		}).then(function(data, status) {
		   for (var i=0; i<data.length; i++){
				var c = new Location(data[i].x, data[i].y);
				document.getElementById(data[i].x+" "+data[i].y).innerHTML = i+1;
				changeColor(c, true);
			}
		   return data;
		});
	});
}

function resetButton(){
	location.reload();
}
