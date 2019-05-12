let range = document.getElementById('display').innerHTML;

if(value >=0 && value <= 50){
    range.style.color='#22d119';

} 


if(value >=51 && value <= 100){
    range.style.color='#529b6d';

} 

if(value >=101 && value <= 250){
    range.style.color='#ffea05';

} 

if(value >=251 && value <= 350){
    range.style.color='#ff9605';

} 

if(value >=351 && value <= 430){
    range.style.color='#ff0511';

} 

if(value >=431 && value <= 10000000){
    range.style.color='#4c031d';

} 