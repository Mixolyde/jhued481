//GLOBALs
var deliveryInputs;

//init globals
function initPage(){
  deliveryInputs = [
  document.getElementById("delivFName"),
  document.getElementById("delivLName"),
  document.getElementById("delivStreet"),
  document.getElementById("delivCity"),
  document.getElementById("delivState"),
  document.getElementById("delivZip"),
  document.getElementById("delivPhone"),
  ];
}


var querydict = {};
location.search.substr(1).split("&").forEach(function(item) {var s = item.split("="), k = s[0], v = s[1] && decodeURIComponent(s[1]); (k in querydict) ? querydict[k].push(v) : querydict[k] = [v]})

function printQueryDict(){
  for (k in querydict)
  {
    document.write(k + "="+querydict[k]+"<br/>\n");
  }
}
//return state codes for drop down
function stateOptions() {
  document.write(`<option value="AK">AK</option>
  <option value="AL">AL</option>
  <option value="AR">AR</option>
  <option value="AZ">AZ</option>
  <option value="CA">CA</option>
  <option value="CO">CO</option>
  <option value="CT">CT</option>
  <option value="DC">DC</option>
  <option value="DE">DE</option>
  <option value="FL">FL</option>
  <option value="GA">GA</option>
  <option value="HI">HI</option>
  <option value="IA">IA</option>
  <option value="ID">ID</option>
  <option value="IL">IL</option>
  <option value="IN">IN</option>
  <option value="KS">KS</option>
  <option value="KY">KY</option>
  <option value="LA">LA</option>
  <option value="MA">MA</option>
  <option value="MD">MD</option>
  <option value="ME">ME</option>
  <option value="MI">MI</option>
  <option value="MN">MN</option>
  <option value="MO">MO</option>
  <option value="MS">MS</option>
  <option value="MT">MT</option>
  <option value="NC">NC</option>
  <option value="ND">ND</option>
  <option value="NE">NE</option>
  <option value="NH">NH</option>
  <option value="NJ">NJ</option>
  <option value="NM">NM</option>
  <option value="NV">NV</option>
  <option value="NY">NY</option>
  <option value="OH">OH</option>
  <option value="OK">OK</option>
  <option value="OR">OR</option>
  <option value="PA">PA</option>
  <option value="RI">RI</option>
  <option value="SC">SC</option>
  <option value="SD">SD</option>
  <option value="TN">TN</option>
  <option value="TX">TX</option>
  <option value="UT">UT</option>
  <option value="VA">VA</option>
  <option value="VT">VT</option>
  <option value="WA">WA</option>
  <option value="WI">WI</option>
  <option value="WV">WV</option>
  <option value="WY">WY</option>`);
}

function monthOptions() {
  document.write(`
	  <option value="1">January</option>
	  <option value="2">February</option>
	  <option value="3">March</option>
	  <option value="4">April</option>
	  <option value="5">May</option>
	  <option value="6">June</option>
	  <option value="7">July</option>
	  <option value="8">August</option>
	  <option value="9">September</option>
	  <option value="10">October</option>
	  <option value="11">November</option>
	  <option value="12">December</option> `);
}

var deliveryEnabled = true;

function sameAddressClick () {
  if (deliveryEnabled == true){
    deliveryEnabled = false;
    switchDeliveryAddressInputs(true, false);
  } else {
    deliveryEnabled = true;
    switchDeliveryAddressInputs(false, true);
  }
}

//set true to disable all inputs
function switchDeliveryAddressInputs(disable, require){
  deliveryInputs.forEach(function (item, index, array) {
    item.disabled = disable;
    item.required = require;
    });
}

// determine if custom message is required
var customRequired = false;
function customClick () {
  if (customRequired == false){
    customRequired = true;
    document.getElementById("customTextArea").required=true;
  } else {
    customRequired = false;
    document.getElementById("customTextArea").required=false;
  }
}

function customValidate(){
  //reset error box
  var errorText = document.getElementById("errorText");
  errorText.innerHTML = "";
  errorText.style.display = "none";
  box1 = document.getElementById("congratsMsg");
  box2 = document.getElementById("birthdayMsg");
  box3 = document.getElementById("anniversaryMsg");
  box4 = document.getElementById("iloveyouMsg");
  box5 = document.getElementById("customMsg");
  if( !box1.checked && !box2.checked && !box3.checked && 
      !box4.checked && !box5.checked) {
    errorText.innerHTML = "You must select at least one message";
    errorText.style.display = "block";
    return false;
  }
  if( document.getElementById("password1").value !=
      document.getElementById("password2").value){
    errorText.innerHTML = "Your passwords must match";
    errorText.style.display = "block";
    return false;
  }
  //test bad validate
  return true;
}
