function parseJSON() {
    var Http = new XMLHttpRequest();
    var url = 'https://api.thingspeak.com/channels/778383/fields/1.json?results=2';
    Http.open("GET", url);
    Http.send();
    var obj;

    Http.onreadystatechange=(e)=>{
        obj = JSON.parse(Http.responseText);

    }
    console.log(obj);
    document.getElementById("aqi").innerHTML = obj.channel.field1;
    document.getElementById("temp").innerText = obj.channel.field2;
    document.getElementById("humidity").innerText = obj.channel.field3;
    document.getElementById("CO2").innerText = obj.channel.field4;
    document.getElementById("PM25").innerText = obj.channel.field5;
    document.getElementById("createdAt").innerText = obj.channel.created_at;
    document.getElementById("updatedAt").innerText = obj.channel.updated_at;

    return obj;
}