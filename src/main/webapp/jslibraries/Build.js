let mobo;
let gpu;
let cpu;
let ram;
let massStorage1;
let massStorage2;
let massStorage3;
let psu;
let pcCase;
let arrayElements;
let formFactor;
let slotSataAvailable;
let slotNVMEAvailable;
let slotRamAvailable;
let powerAvailable;
let socketRam;
let socketCpu;
let compatible;
let selectedElement;
let selectedElementObject;



function selectGPU(){
    selectedElement="Gpus";
    submitForm("Gpus");

}
function selectCPU(){
    selectedElement="Cpus";
    submitForm("Cpus");

}
function selectMOBO(){
    selectedElement="Mobo";
    submitForm("Mobo");

}

function selectRAM(){
    selectedElement="Ram";
    submitForm("Ram");

}
function selectMassStorage(number){
    selectedElement="MassStorage";
    submitForm("Gpus",number);

}

function selectPcCase(){
    selectedElement="PcCase"
    submitForm("Cases");

}
function  selectPsu(){
    selectedElement="Psu"
    submitForm("Psu");

}

function toggleCompatible(){
    compatible=!compatible;
    askDb();
}

function askDb(){
    let formData;
    powerAvailable=psu.power-mobo.consumption-gpu.consumption-ram.consumption
        -massStorage1.consumption-massStorage2.consumption-massStorage3.comsumption-cpu.consumption;
    formData="name='"+$("#productName").val()
    if(compatible){
        if(selectedElement=="Ram")
            formData+="&MEMsocket="+selectedElementObject.socket;
        if(selectedElement=="MassStorage")
            formData+="&MEMsocket="+selectedElementObject.socket;
            formData+="&"

    }

}

function submitForm(item,number) {

    let xhttp = new XMLHttpRequest();
    let formDATA = $("#searchForm").serialize();
    $('tr.removable').remove();

    xhttp.onreadystatechange = function () {

        if (this.readyState == 4 && this.status == 200) {
            console.log(this.responseText);

            var results = JSON.parse(this.responseText);
            arrayElements=results;

            switch (item) {
                case "Gpus":
                  results.forEach(gpuTabler);
                    break;

                case "Cpus":
                     results.forEach(cpuTabler);
                    break;

                case "Ram":
                     results.forEach(memoryTabler,"Ram");
                    break;

                case "MassStorage":
                    results.forEach(memoryTabler,"MassStorage",number);
                    break;

                case "MotherBoards":
                      results.forEach(moboTabler);
                    break;

                case "Cases":
                      results.forEach(pcCaseTabler);
                    break;

                case "Psus":
                      results.forEach(psusTabler);
                    break;
                default:
                    $("#searchResult").html("Cannot visualize");
            }

        }
    };
    xhttp.open("POST", "/MYOPSite_war_exploded/itemLister", true);

    xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");

    xhttp.send(formDATA);
    console.log(formDATA);
}


function gpuTabler(value) {
    var row;
    row = "<tr class='removable'>" +
        "<td class='productName' >" + value.name + "</td>" +
        "<td class='consumption'>" + value.consumption + "</td>" +
        "<td class='price'>" + value.price + "$</td>" +
        "<td><img src=-'" + value.imagePath + "'></td>" +
        buttonAdder(value.id,"Gpu") +
        "</tr>";
    $("#searchResult").append(row);
}

function cpuTabler(value) {
    var row;
    row = "<tr class='removable'>" +
        "<td class='productName'>" + value.name + "</td>" +
        "<td class='socket'>" + value.socket + "</td>"
    if (value.integratedgpu)
        row += "<td class='integratedGpu'>Yes</td>"
    else row += "<td class='integratedGpu'>No</td>"
    row += "<td class='consumption'>" + value.consumption + "</td>" +
        "<td class='price'>" + value.price + "$</td>" +
        "<td class='inStock'>" + value.stock + "</td>" +
        "<td><img src=-'" + value.imagePath + "'></td>" +
        buttonAdder(value.id,"Cpu") +
        "</tr>"
    $("#searchResult").append(row);
}

function memoryTabler(value,type,number) {
    var row;
    row = "<tr class='removable'>" +
        "<td class='productName'>" + value.name + "</td>" +
        "<td class='amountOfMemories'>" + value.amountMemories + "</td>" +
        "<td class='consumption'>" + value.consumption + "</td>" +
        "<td class='price'>" + value.price + "$</td>" +
        "<td><img src=-'" + value.imagePath + "'></td>" +
        buttonAdder(value.id,"type",number) +
        "</tr>"
    $("#searchResult").append(row);
}

function moboTabler(value) {
    var row;
    row = "<tr class='removable'>" +
        "<td class='productName' >" + value.name + "</td>" +
        "<td class='cpuSocket'>" + value.cpuSocket + "</td>" +
        "<td class='ramSocket'>" + value.ramSocket + "</td>" +
        "<td class='ramSlots'>" + value.amountSlotRam + "</td>" +
        "<td class='nvmeSlots'>" + value.amountSlotNvme + "</td>" +
        "<td class='sataSlots'>" + value.amountSlotSata + "</td>" +
        "<td class='formFactor'>" + value.formFactor + "</td>" +
        "<td class='consumption'>" + value.consumption + "</td>" +
        "<td class='price'>" + value.price + "$</td>" +
        "<td><img src=-'" + value.imagePath + "'></td>" +
        buttonAdder(value.id,"Mobo") +
        "</tr>"
    $("#searchResult").append(row);
}

function pcCaseTabler(value) {
    var row;
    row = "<tr class='removable'>" +
        "<td class='productName' >" + value.name + "</td>" +
        "<td class='formFactor'>" + value.formFactor + "</td>" +
        "<td class='price'>" + value.price + "$</td>" +
        "<td><img src=-'" + value.imagePath + "'></td>" +
        buttonAdder(value.id,"pcCase") +
        "</tr>"
    $("#searchResult").append(row);
}

function buildTabler(value) {
    var row;
    row = "<tr class='removable'>" +
        "<td class='moboName'>" + value.mobo + "</td>" +
        "<td  class='gpuName'>" + value.gpu + "</td>" +
        "<td class='cpuName'>" + value.cpu + "</td>" +
        "<td class='caseName'>" + value.pcCase + "</td><td class='memoriesName'>";
    for (let i in value.memories) {
        row += i + "<br>";
    }
    row += "</td>";
    if (value.suggested)
        row += "<td class='isSuggested'>Yes</td>"
    else row += "<td class='isSuggested'>No</td>"

    row += "<td class='buildType'>" + value.type + "</td>" +
        "<td class='maker'>" + value.maker + "</td>" +
        buttonAdder(value.id,"pcCase") +
        "</tr>"
    $("#searchResult").append(row);
}

function psusTabler(value) {
    var row;
    row = "<tr class='removable'>" +
        "<td class='productName' >" + value.name + "</td>" +
        "<td class='power'>" + value.power + "</td>" +
        "<td class='price'>" + value.price + "$</td>" +
        "<td><img src=-'" + value.imagePath + "'></td>" +
        buttonAdder(value.id,"Psu") +
        "</tr>"
    $("#searchResult").append(row);
}


function buttonAdder(id,type,number) {
    let buttonForm;
    let action;
    if(type!=null&&number!=null)
         action="add"+type+number+"("+id+")";
    else{
        action="add"+type+"("+id+")";
    }
    buttonForm = "<td><form id='" + id + "' onclick='"+action+"'>" +
        "<h1 class='btn active'>Add</h1></form></td>";
    return buttonForm;
}


function addGpu(id){
    let elem=arrayElements.find(elem=>elem.id==id);
    gpu=elem;
    selectedElementObject=elem;
    html="<option value='id'>"+elem.name+"</option>"
    $("#gpu").html()
}

function addCpu(id){
    let elem=arrayElements.find(elem=>elem.id==id);
    cpu=elem;
    selectedElementObject=elem;
    html="<option value='id'>"+elem.name+"</option>"
    $("#cpu").html()
}

function addMobo(id){
    let elem=arrayElements.find(elem=>elem.id==id);
    mobo=elem;
    selectedElementObject=elem;
    html="<option value='id'>"+elem.name+"</option>"
    $("#mobo").html()
}
function addpcCase(id){
    let elem=arrayElements.find(elem=>elem.id==id);
    pcCase=elem;
    selectedElementObject=elem;
    html="<option value='id'>"+elem.name+"</option>"
    $("#pcCase").html()
}
function addPsu(id){
    let elem=arrayElements.find(elem=>elem.id==id);
    psu=elem;
    selectedElementObject=elem;
    html="<option value='id'>"+elem.name+"</option>"
    $("#psu").html()
}
function addRam(id){
    let elem=arrayElements.find(elem=>elem.id==id);
    ram=elem;
    selectedElementObject=elem;
    html="<option value='id'>"+elem.name+"</option>"
    $("#ram").html()
}
function addMassStorage1(id){
    let elem=arrayElements.find(elem=>elem.id==id);
    massStorage1=elem;
    selectedElementObject=elem;
    html="<option value='id'>"+elem.name+"</option>"
    $("#massStorage1").html()
}

function addMassStorage2(id){
    let elem=arrayElements.find(elem=>elem.id==id);
    massStorage2=elem;
    selectedElementObject=elem;
    html="<option value='id'>"+elem.name+"</option>"
    $("#massStorage2").html()
}
function addMassStorage3(id){
    let elem=arrayElements.find(elem=>elem.id==id);
    massStorage3=elem;
    selectedElementObject=elem;
    html="<option value='id'>"+elem.name+"</option>"
    $("#massStorage3").html()
}