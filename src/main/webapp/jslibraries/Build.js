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
let formFactorMobo = '';
let slotSataAvailable = '';
let slotNVMEAvailable = '';
let slotRamAvailable = '';
let slotRam;
let slotSata;
let slotNVME;
let slotRamUsed = '';
let slotSataUsed = '';
let slotNVMEUsed = '';
let power;
let powerNeeded = 0;
let socketRam = '';
let socketCpu = '';
let cpuConnector='';
let ramConnector='';
let integratedCpu = '';
let selectedElement;
let selectedElementObject;
let itemCategory;
let tableHeader;
let idBuild;
let submitable;
let formFactorCase="";
let massStorageNumber=0;/*dichiarato qui per poter richiamare il submit Form anche dai pulsanti*/
let price;


function toggleOverlay() {
    $("#overlayForm").fadeToggle();
}

function updateSpecification() {
    if (mobo == null || gpu == null || cpu == null || ram == null || psu == null || pcCase == null || !(massStorage1 != null || massStorage2 != null || massStorage3 != null)) {
        submitable = false;
    } else submitable = true;

    price=0;
    powerNeeded = 0;
    slotNVMEUsed = 0;
    slotRamUsed = 0;
    slotSataUsed = 0;

    if(psu!=null){
        power=psu.power;
        price=psu.price;
    }
    if (mobo != null) {
        price+=mobo.price;
        slotSataAvailable = mobo.amountSlotSata;
        slotSata=mobo.amountSlotSata;
        slotNVMEAvailable = mobo.amountSlotNvme;
        slotNVME=mobo.amountSlotNvme;
        slotRamAvailable = mobo.amountSlotRam;
        slotRam=mobo.amountSlotRam;
        powerNeeded += mobo.consumption;
        socketRam = mobo.ramSocket;
        socketCpu = mobo.cpuSocket;
        formFactorMobo = mobo.formFactor;
    }
    if (pcCase != null) {
        price+=pcCase.price;
        formFactorCase = pcCase.formFactor
    }
    if (gpu != null){
        price+=gpu.price;
        powerNeeded += gpu.consumption
    }
    if (ram != null) {
        ramConnector=ram.socket;
        price+=ram.price;
        powerNeeded += ram.consumption;
        slotRamUsed = ram.amountMemories;
    }
    if (cpu != null){
        cpuConnector=cpu.socket;
        price+=cpu.price;
    powerNeeded += cpu.consumption;
    }

    if (massStorage1 != null) {
        price+=massStorage1.price;
        if (massStorage1 == "sata") {

            slotSataAvailable -= 1;
            slotSataUsed += 1;
        } else {
            slotNVMEAvailable -= 1;
            slotNVMEUsed += 1
        }
        powerNeeded += massStorage1.consumption;
    }
    if (massStorage2 != null) {
        price+=massStorage2.price;
        if (massStorage2 == "sata") {
            slotSataAvailable -= 1;
            slotSataUsed += 1;
        } else {
            slotNVMEAvailable -= 1;
            slotNVMEUsed += 1
        }
        powerNeeded += massStorage2.consumption;
    }
    if (massStorage3 != null) {
        price+=massStorage3.price;
        if (massStorage3 == "sata") {
            slotSataAvailable -= 1;
            slotSataUsed += 1;
        } else {
            slotNVMEAvailable -= 1;
            slotNVMEUsed += 1
        }
        powerNeeded += massStorage3.consumption;
    }
    $("#endPrice").text(price);

}
function checkValidity() {
    if(powerNeeded<=power&&slotRamUsed<=slotRam&&slotNVMEUsed<=slotNVME&&slotSataUsed<=slotSata&&ramConnector.toLowerCase()==socketRam.toLowerCase()&&
    socketCpu==cpuConnector){
        switch (pcCase.getFormFactor().toLowerCase()){
            case"mini-itx":if(moboFormFactor.equals(("micro"))) throw new BuildException("caseSmallerThanMobo");
            case"micro-atx":if(moboFormFactor.equals(("atx"))) throw new BuildException("caseSmallerThanMobo");
            case"atx":if(moboFormFactor.equals("eatx")) throw new BuildException("caseSmallerThanMobo");
        }

    }

}

function selectGPU() {
    toggleOverlay();
    selectedElement = "Gpus";
    itemCategory = "gpus";
    tableHeader = "<tr><th>Product Name</th><th>Power Consumption</th><th>Price</th></tr>";

    submitForm(tableHeader);

}

function selectCPU() {
    toggleOverlay();
    selectedElement = "Cpus";
    itemCategory = "cpus";
    tableHeader = "<tr><th>Product Name</th><th>Socket</th><th>Integrated Gpu</th><th>Power Consumption</th><th>Price</th></tr>"

    submitForm(tableHeader);
}

function selectMOBO() {
    toggleOverlay();
    selectedElement = "MotherBoards";

    itemCategory = "motherboards";
    tableHeader = "<tr><th>Product Name</th><th>Form Factor</th><th>Ram Sockets</th><th>Ram Slots</th><th>NVME Slots</th><th>SATA Slots</th><th>Form Factor</th><th>Power Consumption</th><th>Price</th></tr>"

    submitForm(tableHeader);

}

function selectRAM() {
    toggleOverlay();
    selectedElement = "Ram";

    itemCategory = "memories";
    tableHeader = "<tr><th>Product Name</th><th>Number Of Sticks</th><th>Power Consumption</th><th>Price</th></tr>"


    submitForm(tableHeader);

}

function selectMassStorage(number) {
    toggleOverlay();
    selectedElement = "MassStorage";
    massStorageNumber=number;


    itemCategory = "memories";
    tableHeader = "<tr><th>Product Name</th><th>Number Of Memories</th><th>Power Consumption</th><th>Price</th></tr>"

    submitForm(tableHeader, number);

}

function selectPcCase() {
    toggleOverlay();
    selectedElement = "PcCase"

    itemCategory = "cases";
    tableHeader = "<tr><th>Product Name</th><th>Form Factor</th><th>Price</th></tr>"

    submitForm(tableHeader);

}

function selectPsu() {
    toggleOverlay();
    selectedElement = "Psu"

    itemCategory = "psus";
    tableHeader = "<tr><th>Product Name</th><th>Power</th><th>Price</th></tr>"


    submitForm(tableHeader);

}




function submitForm(headers) {

    $("#ComponentTitle").text(selectedElement);
    $('#searchResultBuild>tbody').html('');
    $('#searchResultBuild>thead').html(headers);
    let formData;
    updateSpecification();


    let compatibilityCheck = $("#compatible").prop("checked")
    let formFactorFlag = false /*Verifica che durante la ricerca per pc Case sia stata cercata la form factor salvata una volta*/
    let formFactor = "";
    let formFactorEnd = true;
    arrayElements=[];

    do {/*il ciclo viene ripetuto solo con pcCase e Motherboards*/
        formData = "name=" + $("#productName").val().replaceAll(" ", "%20");

        if (compatibilityCheck) {/*form con i controlli*/
            if (selectedElement == "MassStorage") {
                $("#massStorageOption").show();
                if ($("#sata").prop("checked")) {
                    formData += "&MEMsocket=sata&amountOfMemories=" + slotSataAvailable;
                    slotNVMEUsed -= 1;
                } else {
                    formData += "&MEMsocket=nvme&amountOfMemories=" + slotNVMEAvailable;
                    slotSataUsed -= 1;
                }
                formData += "&mType=true"
            }
            else    $("#massStorageOption").hide();


            if (selectedElement == "MotherBoards") {/* scalare i vari formati di schede compatibili con i case*/
                if (formFactorFlag) {
                    if (formFactor.toLowerCase() == "eatx") {
                        formFactor = "atx"
                    }else if (formFactor.toLowerCase() == "atx") {
                        formFactor = "micro-atx"
                    }
                    else if (formFactor.toLowerCase() == "micro-atx") {
                        formFactorEnd = true;/*flag per interrompere il ciclo*/
                        formFactor = "mini-itx"
                    }
                } else {
                    formFactorEnd=false;/*abilito il ciclo do while*/
                    formFactor = formFactorCase;/*alla prima chiamata di motherboard viene ricercato il form factor stesso del case che lo deve contenere*/
                    formFactorFlag = true;
                }
            }

            if (selectedElement == "PcCase")

                if (formFactorFlag) {
                    if (formFactor.toLowerCase()=="mini-itx")
                        formFactor = "micro-atx"
                    else if (formFactor.toLowerCase()=="micro-atx") {
                        formFactor = "atx"
                    } else if (formFactor.toLowerCase()=="atx"){
                        formFactor = "eatx"
                        formFactorEnd = true;
                    }
                } else {
                    formFactorEnd=false;
                    formFactor = formFactorMobo;
                    formFactorFlag = true;
                }


            if (selectedElement == "Ram") {
                formData += "&MEMsocket=" + socketRam + "&amountOfMemories=" + slotRamAvailable + "&mType=false";
            }
            formData += "&power=" + powerNeeded + "&id=&formFactor=" + formFactor + "&CPUsocket=" + socketCpu + "&RAMsocket=" + socketRam + "" +
                "&nRAMSockets=" + slotRamUsed + "&nSATASockets=" + slotSataUsed + "&nNVMESockets=" + slotNVMEUsed + "&requestedItem=" + itemCategory +
                "&integratedGpu=" + integratedCpu;
        } else {/* inizio formazione del form senza controlli*/
            if (selectedElement == "Ram") {
                formData += "&mType=false&MEMsocket=";
            }
            if (selectedElement == "MassStorage") {
                formData+="&mType=false"
                $("#massStorageOption").show();
                if ($("#sata").prop("checked")) {
                    formData += "&MEMsocket=sata"
                    }else formData += "&MEMsocket=nvme"
        }else    $("#massStorageOption").hide();

            formData += "&requestedItem=" + itemCategory + "&power=&id=&formFactor=&CPUsocket=&RAMsocket=" +
                "&nRAMSockets=" + "&nSATASockets=&nNVMESockets=&amountOfMemories=&integratedGpu=";
        }
        let xhttp = new XMLHttpRequest();

        xhttp.onreadystatechange = function () {

            if (this.readyState == 4 && this.status == 200) {

                console.log(this.responseText);

                var results = JSON.parse(this.responseText);

                switch (selectedElement) {
                    case "Gpus":
                       arrayElements= results;
                        results.forEach(gpuTabler);
                        break;

                    case "Cpus":
                        arrayElements= results;
                        results.forEach(cpuTabler);
                        break;

                    case "Ram":
                        arrayElements= results;
                        results.forEach(memoryTabler, {type: "Ram"});
                        break;

                    case "MassStorage":
                        arrayElements= results;
                        results.forEach(memoryTabler, {type: "MassStorage"});
                        break;

                    case "MotherBoards":
                        results.forEach(addToResults)/*non effettuo il push direttamente qui per problemi di conversione*/
                        results.forEach(moboTabler);
                        break;

                    case "PcCase":
                        results.forEach(addToResults)
                        results.forEach(pcCaseTabler);
                        break;

                    case "Psu":
                        arrayElements= results;
                        results.forEach(psusTabler);
                        break;
                    default:
                        $("#searchResultBuild").html("Cannot visualize");
                }


            }
        }
        xhttp.open("POST", "/MYOPSite_war_exploded/itemsLister", true);
        xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        setTimeout(xhttp.send(formData),500);
        console.log(formData);
    } while ((selectedElement == "MotherBoards" || selectedElement == "PcCase") && !formFactorEnd);
}

function addToResults(value){
    arrayElements.push(value);
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
        buttonAdder(value.id, this.type,massStorageNumber) +
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
        buttonAdder(value.id, "MotherBoards") +
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
        buttonAdder(value.id, "PcCase") +
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
    if (type != null && number != 0&&number!=null)
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
    $("#gpu").val(elem.name)
    toggleOverlay();
    checkDisableSubmit();

}

function addCpu(id) {
    let elem = arrayElements.find(elem => elem.id == id);
    cpu = elem;
    selectedElementObject = elem;
    $("#cpu").val(elem.name)
    toggleOverlay();
    checkDisableSubmit();
}

function addMotherBoards(id) {
    let elem = arrayElements.find(elem => elem.id == id);
    mobo = elem;
    selectedElementObject = elem;
    $("#mobo").val(elem.name)
    toggleOverlay();
    checkDisableSubmit();
}

function addPcCase(id) {
    let elem = arrayElements.find(elem => elem.id == id);
    pcCase = elem;
    selectedElementObject = elem;
    $("#pcCase").val(elem.name)
    toggleOverlay();
    checkDisableSubmit();
}

function addPsu(id) {
    let elem = arrayElements.find(elem => elem.id == id);
    psu = elem;
    selectedElementObject = elem;
    $("#psu").val(elem.name)
    toggleOverlay();
    checkDisableSubmit();
}

function addRam(id) {
    let elem = arrayElements.find(elem => elem.id == id);
    ram = elem;
    selectedElementObject = elem;
    $("#ram").val(elem.name)
    toggleOverlay();
    checkDisableSubmit();
}

function addMassStorage1(id) {
    let elem = arrayElements.find(elem => elem.id == id);
    massStorage1 = elem;
    selectedElementObject = elem;
    $("#massStorage1").val(elem.name)

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
    $("#massStorage3").val(elem.name)

    toggleOverlay();
}

