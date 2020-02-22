/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function showFileName( event ) {
    var input = event.srcElement;
    var no_files=document.getElementById("no-files");
    var name_files=document.getElementById("name-files");
    var name_files_list=document.getElementById("name-files-list");
    var boton=document.getElementById("boton");
    no_files.style.display="none";
    name_files.style.display="";
    name_files_list.innerHTML="";
    boton.disabled=false;
    for(var i=0;i<input.files.length;i++){
        name_files_list.innerHTML+="<p class='list-item is-gray'>"+input.files[i].name+"</p>";
    }
}

function disableAll(){
    var no_files=document.getElementById("no-files");
    var name_files=document.getElementById("name-files");
    var boton=document.getElementById("boton");
    no_files.style.display="";
    name_files.style.display="none";
    boton.disabled=true;
}

function fileValidation(event){
    var fileInput = document.getElementById('file-input');
    var filePath = fileInput.value;
    var allowedExtensions = /(.java)$/i;
    if(!allowedExtensions.exec(filePath)){
        alert('Agrega solo archivos con extensión .java');
        fileInput.value = '';
        disableAll();
        return false;
    }else{
        showFileName(event);
    }
}

function refresh() {
    var e = document.getElementById("refreshed");
    if (e.value === "no")
        e.value = "yes";
    else {
        e.value = "no";
        location.reload();
    }
}

