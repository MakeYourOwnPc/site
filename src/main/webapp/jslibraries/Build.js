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
let selectedElement;
let selectedElementObject;
let itemCategory;
let tableHeader;
let item;


function toggleOverlay() {
    $("#overlayForm").fadeToggle();
}

function updateSpecification() {
    slotNVMEUsed = 0;
    slotRamUsed = 0;
    slotSataUsed = 0;
    powerNeeded = mobo.consumption + gpu.consumption + ram.consumption
        + massStorage1.consumption + massStorage2.consumption + massStorage3.comsumption + cpu.consumption;
    slotSataAvailable = mobo.amountSlotSata;
    slotNVMEAvailable = mobo.amountSlotNvme;
    slotRamAvailable = mobo.amountSlotRam;
    slotRamUsed = ram.amountOfMemories;

    socketRam = mobo.ramSocket;
    socketCpu = mobo.cpuSocket;
    formFactor = mobo.formFactor;
    if (massStorage1 != null) {
        if (massStorage1 == "sata") {
            slotSataAvailable -= 1;
            slotSataUsed += 1;
        } else {
            slotNVMEAvailable -= 1;
            slotNVMEUsed += 1
        }
    }
    if (massStorage2 != null) {
        if (massStorage2 == "sata") {
            slotSataAvailable -= 1;
            slotSataUsed += 1;
        } else {
            slotNVMEAvailable -= 1;
            slotNVMEUsed += 1
        }
    }
    if (massStorage3 != null) {
        if (massStorage3 == "sata") {
            slotSataAvailable -= 1;
            slotSataUsed += 1;
        } else {
            slotNVMEAvailable -= 1;
            slotNVMEUsed += 1
        }
    }

}


function selectGPU() {
    toggleOverlay();
    selectedElement = "Gpus";
    itemCategory = "gpus";
    tableHeader = "<tr><th>Product Name</th><th>Power Consumption</th><th>Price</th></tr>";
    item = "Gpus";
    submitForm();

}

function selectCPU() {
    toggleOverlay();
    selectedElement = "Cpus";
    itemCategory = "cpus";
    tableHeader = "<tr><th>Product Name</th><th>Socket</th><th>Integrated Gpu</th><th>Power Consumption</th><th>Price</th></tr>"
    item = "Cpus";
    submitForm();
}

function selectMOBO() {
    toggleOverlay();
    selectedElement = "Mobo";

    itemCategory = "motherboards";
    tableHeader = "<tr><th>Product Name</th><th>Form Factor</th><th>Ram Sockets</th><th>Ram Slots</th><th>NVME Slots</th><th>SATA Slots</th><th>Form Factor</th><th>Power Consumption</th><th>Price</th></tr>"
    item = "MotherBoards";
    submitForm();

}

function selectRAM() {
    toggleOverlay();
    selectedElement = "Ram";

    itemCategory = "memories";
    tableHeader = "<tr><th>Product Name</th><th>Socket</th><th>Number Of Sticks</th><th>Power Consumption</th><th>Price</th></tr>"
    item = "Ram";

    submitForm();

}

function selectMassStorage(number) {
    toggleOverlay();
    selectedElement = "MassStorage";

    itemCategory = "memories";
    tableHeader = "<tr><th>Product Name</th><th>Power Consumption</th><th>Price</th></tr>"
    item = "MassStorage";
    submitForm(number);

}

function selectPcCase() {
    toggleOverlay();
    selectedElement = "PcCase"

    itemCategory = "cases";
    tableHeader = "<tr><th>Product Name</th><th>Form Factor</th><th>Price</th></tr>"
    item = "Cases";
    submitForm();

}

function selectPsu() {
    toggleOverlay();
    selectedElement = "Psu"

    itemCategory = "psus";
    tableHeader = "<tr><th>Product Name</th><th>Power</th><th>Price</th></tr>"
    item = "Psu";

    submitForm();

}


function submitForm(number) {


    let xhttp = new XMLHttpRequest();
    $('tr.removable').remove();

    let formData;
 /*   updateSpecification();*/

    formData = "name=" + $("#productName").val().replaceAll(" ", "%20");
    let socketEmpty = true;


    if (selectedElement == "MassStorage") {
        if ($("#sata").prop("checked")) {
            formData += "&socket=sata&amountOfMemories=" + slotSataAvailable;
            slotNVMEUsed -= 1;
        } else {
            formData += "&socket=nvme&amountOfMemories=" + slotNVMEAvailable;
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

        formData += "&power=" + powerNeeded + "&id=&formFactor=" + formFactor + "&CPUsocket=" + socketCpu + "&RAMsocket=" + socketRam + "" +
            "&nRAMSocket=" + slotRamUsed + "" + "&nSATASockets=" + slotSataUsed + "&nNVMESockets=" + slotNVMEUsed + "&requestedItem=" + itemCategory;
    } else
        formData += "&requestedItem=" + itemCategory + "&power=&id=&formFactor=&CPUsocket=&RAMsocket=" +
            "&nRAMSockets=" + "&nSATASockets=&nNVMESockets=&MEMsocket=&amountOfMemories=&mType=false&integratedGpu=";

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
                    $("#searchResult").html("Cannot visualize");
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
        buttonAdder(value.id, "Cpu") +
        "</tr>"
    $("#searchResult").append(row);
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
        buttonAdder(value.id, "Mobo") +
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
        buttonAdder(value.id, "pcCase") +
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
        buttonAdder(value.id, "pcCase") +
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
        buttonAdder(value.id, "Psu") +
        "</tr>"
    $("#searchResult").append(row);
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
    $("#gpu").html("<option value='id'>" + elem.name + "</option>")
    toggleOverlay();

}

function addCpu(id) {
    let elem = arrayElements.find(elem => elem.id == id);
    cpu = elem;
    selectedElementObject = elem;
    $("#cpu").html("<option value='id'>" + elem.name + "</option>")
    toggleOverlay();
}

function addMobo(id) {
    let elem = arrayElements.find(elem => elem.id == id);
    mobo = elem;
    selectedElementObject = elem;
    $("#mobo").html("<option value='id'>" + elem.name + "</option>")
    toggleOverlay();
}

function addpcCase(id) {
    let elem = arrayElements.find(elem => elem.id == id);
    pcCase = elem;
    selectedElementObject = elem;
    $("#pcCase").html("<option value='id'>" + elem.name + "</option>")
    toggleOverlay();
}

function addPsu(id) {
    let elem = arrayElements.find(elem => elem.id == id);
    psu = elem;
    selectedElementObject = elem;
    $("#psu").html("<option value='id'>" + elem.name + "</option>")
    toggleOverlay();
}

function addRam(id) {
    let elem = arrayElements.find(elem => elem.id == id);
    ram = elem;
    selectedElementObject = elem;
    $("#ram").html("<option value='id'>" + elem.name + "</option>")
    toggleOverlay();
}

function addMassStorage1(id) {
    let elem = arrayElements.find(elem => elem.id == id);
    massStorage1 = elem;
    selectedElementObject = elem;
    $("#massStorage1").html("<option value='id'>" + elem.name + "</option>");
    toggleOverlay();
}

function addMassStorage2(id) {
    let elem = arrayElements.find(elem => elem.id == id);
    massStorage2 = elem;
    selectedElementObject = elem;
    $("#massStorage2").html("<option value='id'>" + elem.name + "</option>")
    toggleOverlay();
}

function addMassStorage3(id) {
    let elem = arrayElements.find(elem => elem.id == id);
    massStorage3 = elem;
    selectedElementObject = elem;
    $("#massStorage3").html("<option value='id'>" + elem.name + "</option>")
    toggleOverlay();
}

function saveBuild() {
    let build = {
        mobo: mobo.id,
        gpu: gpu.id,
        cpu: cpu.id,
        pcCase: pcCase.id,
        psu: psu.id,
        type: "",
        memories: [massStorage1.id, massStorage2.id, massStorage3.id],
        suggested:false
    }
    let xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {

        if (this.readyState == 4 && this.status == 200) {
            console.log("server response" +this.responseText);
        } else if (this.readyState == 4 && this.status == 404) {
            console.log("error");
            $("#searchResult").append("Saving Error");
        }
    };

    xhttp.open("POST", "/MYOPSite_war_exploded/saveBuild", true);
    xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    let sentData = JSON.stringify(build);
    console.log(sentData);
    xhttp.send("build="+sentData);

}