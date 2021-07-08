let mobo = null;
let gpu = null;
let cpu = null;
let ram = null;
let massStorage1 = null;
let massStorage2 = null;
let massStorage3 = null;
let psu = null;
let pcCase = null;
let arrayElements = null;
let formFactor = '';
let slotSataAvailable = '';
let slotNVMEAvailable = '';
let slotRamAvailable = '';
let slotRamUsed = '';
let slotSataUsed = '';
let slotNVMEUsed = '';
let powerNeeded = 0;
let socketRam = '';
let socketCpu = '';
let integratedCpu='';
let selectedElement;
let selectedElementObject;
let itemCategory;
let tableHeader;
let item;
let idBuild;
let submitable;


function toggleOverlay() {
    $("#overlayForm").fadeToggle();
}

function updateSpecification() {
    if(mobo==null||gpu==null||cpu==null||ram==null||psu==null||pcCase==null||!(massStorage1!=null || massStorage2!=null || massStorage3!=null)){
        submitable=false;
    }
    else submitable=true;

    powerNeeded =0;
    slotNVMEUsed = 0;
    slotRamUsed = 0;
    slotSataUsed = 0;
    if(mobo!=null) {
        slotSataAvailable = mobo.amountSlotSata;
        slotNVMEAvailable = mobo.amountSlotNvme;
        slotRamAvailable = mobo.amountSlotRam;
        powerNeeded += mobo.consumption;
        socketRam = mobo.ramSocket;
        socketCpu = mobo.cpuSocket;
        formFactor = mobo.formFactor;
    }
    if(gpu!=null)
    powerNeeded+=gpu.consumption
    if(ram!=null) {
        powerNeeded += ram.consumption;
        slotRamUsed = ram.amountMemories;
    }
    if(cpu!=null)
        powerNeeded+=cpu.consumption;

    if (massStorage1 != null) {
        if (massStorage1 == "sata") {

            slotSataAvailable -= 1;
            slotSataUsed += 1;
        } else {
            slotNVMEAvailable -= 1;
            slotNVMEUsed += 1
        }
        powerNeeded+= massStorage1.consumption;
    }
    if (massStorage2 != null) {
        if (massStorage2 == "sata") {
            slotSataAvailable -= 1;
            slotSataUsed += 1;
        } else {
            slotNVMEAvailable -= 1;
            slotNVMEUsed += 1
        }
        powerNeeded+= massStorage2.consumption;
    }
    if (massStorage3 != null) {
        if (massStorage3 == "sata") {
            slotSataAvailable -= 1;
            slotSataUsed += 1;
        } else {
            slotNVMEAvailable -= 1;
            slotNVMEUsed += 1
        }
        powerNeeded+= massStorage3.consumption;
    }

}
function selectGPU() {
    toggleOverlay();
    selectedElement = "Gpus";
    itemCategory = "gpus";
    tableHeader = "<tr><th>Product Name</th><th>Power Consumption</th><th>Price</th></tr>";
    item = "Gpus";
    submitForm(tableHeader);

}

function selectCPU() {
    toggleOverlay();
    selectedElement = "Cpus";
    itemCategory = "cpus";
    tableHeader = "<tr><th>Product Name</th><th>Socket</th><th>Integrated Gpu</th><th>Power Consumption</th><th>Price</th></tr>"
    item = "Cpus";
    submitForm(tableHeader);
}

function selectMOBO() {
    toggleOverlay();
    selectedElement = "Mobo";

    itemCategory = "motherboards";
    tableHeader = "<tr><th>Product Name</th><th>Form Factor</th><th>Ram Sockets</th><th>Ram Slots</th><th>NVME Slots</th><th>SATA Slots</th><th>Form Factor</th><th>Power Consumption</th><th>Price</th></tr>"
    item = "MotherBoards";
    submitForm(tableHeader);

}

function selectRAM() {
    toggleOverlay();
    selectedElement = "Ram";

    itemCategory = "memories";
    tableHeader = "<tr><th>Product Name</th><th>Number Of Sticks</th><th>Power Consumption</th><th>Price</th></tr>"
    item = "Ram";

    submitForm(tableHeader);

}

function selectMassStorage(number) {
    toggleOverlay();
    selectedElement = "MassStorage";

    itemCategory = "memories";
    tableHeader = "<tr><th>Product Name</th><th>Number Of Memories</th><th>Power Consumption</th><th>Price</th></tr>"
    item = "MassStorage";
    submitForm(tableHeader,number);

}

function selectPcCase() {
    toggleOverlay();
    selectedElement = "PcCase"

    itemCategory = "cases";
    tableHeader = "<tr><th>Product Name</th><th>Form Factor</th><th>Price</th></tr>"
    item = "Cases";
    submitForm(tableHeader);

}

function selectPsu() {
    toggleOverlay();
    selectedElement = "Psu"

    itemCategory = "psus";
    tableHeader = "<tr><th>Product Name</th><th>Power</th><th>Price</th></tr>"
    item = "Psu";

    submitForm(tableHeader);

}


function submitForm(headers,number) {


    let xhttp = new XMLHttpRequest();
    $('#searchResultBuild>tbody').html('');
    $('#searchResultBuild>thead').html(headers);


    let formData;
   updateSpecification();

    formData = "name=" + $("#productName").val().replaceAll(" ", "%20");
    let socketEmpty = true;


    if (selectedElement == "MassStorage") {
        if ($("#sata").prop("checked")) {
            formData += "&MEMsocket=sata&amountOfMemories=" + slotSataAvailable;
            slotNVMEUsed -= 1;
        } else {
            formData += "&MEMsocket=nvme&amountOfMemories=" + slotNVMEAvailable;
            slotSataUsed -= 1;
        }
        formData += "&mType=true"
        socketEmpty = false;
    }
    if ($("#compatible").prop("checked")) {
        if (selectedElement == "Ram") {
            socketEmpty = false;
            formData += "&MEMsocket=" + socketRam + "&amountOfMemories=" + slotRamAvailable + "&mType=false";
        }

        formData += "&power=" + powerNeeded +"&id=&formFactor=" + formFactor + "&CPUsocket=" + socketCpu + "&RAMsocket=" + socketRam + "" +
            "&nRAMSockets=" + slotRamUsed + "&nSATASockets=" + slotSataUsed + "&nNVMESockets=" + slotNVMEUsed + "&requestedItem=" + itemCategory+
        "&integratedGpu="+integratedCpu;
    } else
        formData += "&requestedItem=" + itemCategory + "&power=&id=&formFactor=&CPUsocket=&RAMsocket=" +
            "&nRAMSockets=" + "&nSATASockets=&nNVMESockets=&MEMsocket=&amountOfMemories=&integratedGpu=";

    xhttp.onreadystatechange = function () {

        if (this.readyState == 4 && this.status == 200) {

            console.log(this.responseText);

            var results = JSON.parse(this.responseText);
            arrayElements = results;

            switch (item) {
                case "Gpus":
                    results.forEach(gpuTabler);
                    break;

                case "Cpus":
                    results.forEach(cpuTabler);
                    break;

                case "Ram":
                    results.forEach(memoryTabler, {type: "Ram"});
                    break;

                case "MassStorage":
                    results.forEach(memoryTabler, {type: "MassStorage", number: number});
                    break;

                case "MotherBoards":
                    results.forEach(moboTabler);
                    break;

                case "Cases":
                    results.forEach(pcCaseTabler);
                    break;

                case "Psu":
                    results.forEach(psusTabler);
                    break;
                default:
                    $("#searchResultBuild").html("Cannot visualize");
            }


        }
    };
    xhttp.open("POST", "/MYOPSite_war_exploded/itemsLister", true);

    xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");

    xhttp.send(formData);
    console.log(formData);
}


function gpuTabler(value) {
    var row;
    row = "<tr class='removable'>" +
        "<td class='productName' >" + value.name + "</td>" +
        "<td class='consumption'>" + value.consumption + "</td>" +
        "<td class='price'>" + value.price + "$</td>" +
        "<td><img src=-'" + value.imagePath + "'></td>" +
        buttonAdder(value.id, "Gpu") +
        "</tr>";
    $("#searchResultBuild").append(row);
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
        buttonAdder(value.id, "Cpu") +
        "</tr>"
    $("#searchResultBuild").append(row);
}

function memoryTabler(value) {
    var row;
    row = "<tr class='removable'>" +
        "<td class='productName'>" + value.name + "</td>" +
        "<td class='amountOfMemories'>" + value.amountMemories + "</td>" +
        "<td class='consumption'>" + value.consumption + "</td>" +
        "<td class='price'>" + value.price + "$</td>" +
        "<td><img src=-'" + value.imagePath + "'></td>" +
        buttonAdder(value.id, this.type, this.number) +
        "</tr>"
    $("#searchResultBuild").append(row);
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
        buttonAdder(value.id, "Mobo") +
        "</tr>"
    $("#searchResultBuild").append(row);
}

function pcCaseTabler(value) {
    var row;
    row = "<tr class='removable'>" +
        "<td class='productName' >" + value.name + "</td>" +
        "<td class='formFactor'>" + value.formFactor + "</td>" +
        "<td class='price'>" + value.price + "$</td>" +
        "<td><img src=-'" + value.imagePath + "'></td>" +
        buttonAdder(value.id, "pcCase") +
        "</tr>"
    $("#searchResultBuild").append(row);
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
        buttonAdder(value.id, "pcCase") +
        "</tr>"
    $("#searchResultBuild").append(row);
}

function psusTabler(value) {
    var row;
    row = "<tr class='removable'>" +
        "<td class='productName' >" + value.name + "</td>" +
        "<td class='power'>" + value.power + "</td>" +
        "<td class='price'>" + value.price + "$</td>" +
        "<td><img src=-'" + value.imagePath + "'></td>" +
        buttonAdder(value.id, "Psu") +
        "</tr>"
    $("#searchResultBuild").append(row);
}


function buttonAdder(id, type, number) {
    let buttonForm;
    let action;
    if (type != null && number != null)
        action = "add" + type + number + "(" + id + ")";
    else {
        action = "add" + type + "(" + id + ")";
    }
    buttonForm = "<td><form id='" + id + "' onclick='" + action + "'>" +
        "<h1 class='btn active'>Add</h1></form></td>";
    return buttonForm;
}


function addGpu(id) {

    let elem = arrayElements.find(elem => elem.id == id);
    gpu = elem;
    selectedElementObject = elem;
    $("#gpu").val( elem.name)
    toggleOverlay();
    checkDisableSubmit();

}

function addCpu(id) {
    let elem = arrayElements.find(elem => elem.id == id);
    cpu = elem;
    selectedElementObject = elem;
    $("#cpu").val( elem.name)
    toggleOverlay();
    checkDisableSubmit();
}

function addMobo(id) {
    let elem = arrayElements.find(elem => elem.id == id);
    mobo = elem;
    selectedElementObject = elem;
    $("#mobo").val( elem.name)
    toggleOverlay();
    checkDisableSubmit();
}

function addpcCase(id) {
    let elem = arrayElements.find(elem => elem.id == id);
    pcCase = elem;
    selectedElementObject = elem;
    $("#pcCase").val( elem.name)
    toggleOverlay();
    checkDisableSubmit();
}

function addPsu(id) {
    let elem = arrayElements.find(elem => elem.id == id);
    psu = elem;
    selectedElementObject = elem;
    $("#psu").val( elem.name)
    toggleOverlay();
    checkDisableSubmit();
}

function addRam(id) {
    let elem = arrayElements.find(elem => elem.id == id);
    ram = elem;
    selectedElementObject = elem;
    $("#ram").val( elem.name)
    toggleOverlay();
    checkDisableSubmit();
}

function addMassStorage1(id) {
    let elem = arrayElements.find(elem => elem.id == id);
    massStorage1 = elem;
    selectedElementObject = elem;
    $("#massStorage1").val( elem.name)
    toggleOverlay();
    checkDisableSubmit();
}

function addMassStorage2(id) {
    let elem = arrayElements.find(elem => elem.id == id);
    massStorage2 = elem;
    selectedElementObject = elem;
    $("#massStorage2").val(elem.name)
    toggleOverlay();
}

function addMassStorage3(id) {
    let elem = arrayElements.find(elem => elem.id == id);
    massStorage3 = elem;
    selectedElementObject = elem;
    $("#massStorage3").val( elem.name)
    toggleOverlay();
}

