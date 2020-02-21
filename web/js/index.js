/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function showFileName( event ) {
     var input = event.srcElement;
     var file=document.getElementById("nom-file");
     var e=input.files.length;
     
     file.textContent = input.files[0].name;
}

