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

function selectGPU(){

}
function selectCPU(){

}
function selectMOBO(){

}

function selectRAM(){

}
function selectMassStorage(number){

}

function selectPcCase(){

}
function  selectPsu(){

}


function askDb(){

}

function submitForm() {

    let xhttp = new XMLHttpRequest();
    let formDATA = $("#searchForm").serialize();
    $('tr.removable').remove();

    xhttp.onreadystatechange = function () {

        if (this.readyState == 4 && this.status == 200) {
            console.log(this.responseText);

            var results = JSON.parse(this.responseText);
            arrayElements=results;

            switch (selectedElement) {
                case "Gpus":
                    results.forEach(gpuTabler);
                    break;

                case "Cpus":
                    results.forEach(cpuTabler);
                    break;

                case "Memories":
                    results.forEach(memoryTabler);
                    break;

                case "Builds":
                    results.forEach(buildTabler);
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
    xhttp.open("POST", "/MYOPSite_war_exploded/adminpage", true);

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
        "<td class='inStock'>" + value.stock + "</td>" +
        "<td><img src=-'" + value.imagePath + "'></td>" +
        buttonAdder(value.id) +
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
        buttonAdder(value.id) +
        "</tr>"
    $("#searchResult").append(row);
}

function memoryTabler(value) {
    var row;
    row = "<tr class='removable'>" +
        "<td class='productName'>" + value.name + "</td>" +
        "<td class='socket'>" + value.socket + "</td>";
    if (value.mType)
        row += "<td class='memoryType'>Mass Storage</td>"
    else row += "<td class='memoryType'>Ram</td>"

    row += "<td class='amountOfMemories'>" + value.amountMemories + "</td>" +
        "<td class='consumption'>" + value.consumption + "</td>" +
        "<td class='price'>" + value.price + "$</td>" +
        "<td class='inStock'>" + value.stock + "</td>" +
        "<td><img src=-'" + value.imagePath + "'></td>" +
        buttonAdder(value.id) +
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
        "<td class='inStock'>" + value.stock + "</td>" +
        "<td><img src=-'" + value.imagePath + "'></td>" +
        buttonAdder(value.id) +
        "</tr>"
    $("#searchResult").append(row);
}

function pcCaseTabler(value) {
    var row;
    row = "<tr class='removable'>" +
        "<td class='productName' >" + value.name + "</td>" +
        "<td class='formFactor'>" + value.formFactor + "</td>" +
        "<td class='price'>" + value.price + "$</td>" +
        "<td class='inStock'>" + value.stock + "</td>" +
        "<td><img src=-'" + value.imagePath + "'></td>" +
        buttonAdder(value.id) +
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
        buttonAdder(value.id) +
        "</tr>"
    $("#searchResult").append(row);
}

function psusTabler(value) {
    var row;
    row = "<tr class='removable'>" +
        "<td class='productName' >" + value.name + "</td>" +
        "<td class='power'>" + value.power + "</td>" +
        "<td class='price'>" + value.price + "$</td>" +
        "<td class='inStock'>" + value.stock + "</td>" +
        "<td><img src=-'" + value.imagePath + "'></td>" +
        buttonAdder(value.id) +
        "</tr>"
    $("#searchResult").append(row);
}

function buttonAdder(id) {
    let buttonForm;
    let requestedItem = $("#requestedItem").attr("value");
    buttonForm = "<td><form id='" + id + "' onclick='viewItem( " + id + ")'>" +
        "<input type='hidden' name='id' value='" + id + "'>" +
        "<input type='hidden' name='option' value='update'>" +
        "<input type='hidden'  name='requestedItem' value='" + requestedItem + "'>" +
        "<h1 class='btn active'>Modify</h1></form></td>";
    return buttonForm;
}


function viewItem(id) {
    let formData = $("#" + id).serialize()
    console.log(formData);
    $.ajax({
        url: "./showItem",
        type: 'POST',
        data: formData,
        beforeSend: function (x) {
            x.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        },
        success: function (data) {
            console.log(data);
            let item = JSON.parse(data);
            prepareFormUpdate(item)
        },
        cache: false,
        contentType: false,
        processData: false
    });
}