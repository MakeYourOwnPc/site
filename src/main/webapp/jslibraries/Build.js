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


function toggleOverlayModify() {
    $("#overlayForm").fadeToggle();
}

function updateSpecification() {

    if (mobo == null  || cpu == null||(gpu==null &&cpu.integratedGpu==false) || ram == null || psu == null || pcCase == null || !(massStorage1 != null || massStorage2 != null || massStorage3 != null)) {
        submitable = false;
    } else submitable = true;

    slotSataAvailable=0;
    slotNVMEAvailable=0;
    slotRamAvailable=0;
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
        if (massStorage1.socket == "sata") {

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
        if (massStorage2.socket == "sata") {
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
        if (massStorage3.socket == "sata") {
            slotSataAvailable -= 1;
            slotSataUsed += 1;
        } else {
            slotNVMEAvailable -= 1;
            slotNVMEUsed += 1
        }
        powerNeeded += massStorage3.consumption;
    }
    $("#endPrice").text(price.toFixed(2));

}
function checkValidity() {
    updateSpecification();

    if(!(massStorage1 != null || massStorage2 != null || massStorage3 != null))/* Nessuna Mass Storage è selezionata*/
    {
        createToast("Invalid Build","No Mass Storage Selected");
    }
    if(!submitable) {

        createToast("Invalid Build","No Important Component Selected");
        return;
    }
    if(gpu==null){
        createToast("Invalid Build","No Gpu Selected With Cpu Without Integrated Gpu ");
        submitable=false;
        return ;
    }
    if(powerNeeded>power){
        createToast("Invalid Build","PSU too weak:PSU:"+power+"/Consumed:"+powerNeeded);
        submitable=false;
        return;
    }
    if(slotRamUsed>slotRam){
        createToast("Invalid Build","Too many Ram Sticks for Motherboard");
        submitable=false;
        return;
    }
    if(slotNVMEUsed>slotNVME){
        createToast("Invalid Build","Too many NVME Memories for Motherboard");
        submitable=false;
        return;
    }
    if(slotSataUsed>slotSata){
        createToast("Invalid Build","Too many SATA Memories for Motherboard");
        submitable=false;
        return;
    }
    if(ramConnector.toLowerCase()!=socketRam.toLowerCase()){
        createToast("Invalid Build","Ram incompatible with motherboard"+ramConnector+"/"+socketRam);
        submitable=false;
        return;
    }
    if(socketCpu!=cpuConnector){
        createToast("Invalid Build","CPU incompatible with motherboard"+cpuConnector+"/"+socketCpu);
        submitable=false;
        return;
    }


    switch (formFactorCase.toLowerCase()){
        case"mini-itx":if(formFactorMobo.toLowerCase()==("micro"))
        {
            createToast("Invalid Build","Case incompatible with Motherboard");
            submitable=false;
            return submitable;
        }
        case"micro-atx":if(formFactorMobo.toLowerCase()==("atx")){
            createToast("Invalid Build","Case incompatible with Motherboard");
            submitable=false;
            return submitable;
        }
        case"atx":if(formFactorMobo.toLowerCase()==("eatx")){
            createToast("Invalid Build","Case incompatible with Motherboard");
            submitable=false;
            return submitable;
        }

    }

    if($("#buildType")=="") {
        createToast("Invalid Build", "Build Type Not Selected")
        return submitable=false;
    }
    if(!checkStock())
        return submitable=false;
    updateBuildSubmitButton();
    return submitable = true;


}

function checkStock(){
    if(gpu!=undefined&&gpu.stock==0){
        createToast("Out Of Stock",gpu.name+" Out Of Stock");
        return false;
    }
    if(cpu.stock==0){
        createToast("Out Of Stock",cpu.name+" Out Of Stock");
        return false;
    }
    if(mobo.stock==0){
        createToast("Out Of Stock",mobo.name+" Out Of Stock");
        return false;
    }

    if(massStorage1!=undefined&&massStorage1.stock==0){
        createToast("Out Of Stock",massStorage1.name+" Out Of Stock");
        return false;
    }
    if(massStorage2!=undefined&&massStorage2.stock==0){
        createToast("Out Of Stock",massStorage2.name+" Out Of Stock");
        return false;
    }
    if(massStorage3!=undefined&&massStorage3.stock==0){
        createToast("Out Of Stock",massStorage3.name+" Out Of Stock");
        return false;
    }
    if(massStorage3!=undefined&&massStorage3.stock==0){
        createToast("Out Of Stock",massStorage3.name+" Out Of Stock");
        return false;
    }
    if(psu.stock==0){
        createToast("Out Of Stock",psu.name+" Out Of Stock");
        return false;
    }
    if(ram.stock==0){
        createToast("Out Of Stock",ram.name+" Out Of Stock");
        return false;
    }
    return true

}
function updateBuildSubmitButton(){
    if(!submitable){
        $("#saveBuild").removeClass("active").addClass("disabled-btn");
    }
    else
        $("#saveBuild").removeClass("disabled-btn").addClass("active");
}

function selectGPU() {
    toggleOverlayBuild();
    selectedElement = "Gpus";
    itemCategory = "gpus";
    tableHeader = "<tr><th>Product Name</th><th>Power Consumption</th><th>Price</th></tr>";
    $(".removeButton").hide();

    submitForm(tableHeader);

}

function selectCPU() {
    toggleOverlayBuild();
    selectedElement = "Cpus";
    itemCategory = "cpus";
    tableHeader = "<tr><th>Product Name</th><th>Socket</th><th>Integrated Gpu</th><th>Power Consumption</th><th>Price</th></tr>"
    $(".removeButton").hide();

    submitForm(tableHeader);
}

function selectMOBO() {
    toggleOverlayBuild();
    selectedElement = "MotherBoards";
    itemCategory = "motherboards";
    tableHeader = "<tr><th>Product Name</th><th>CPU Socket</th><th>Ram Sockets</th><th>Ram Slots</th><th>NVME Slots</th><th>SATA Slots</th><th>Form Factor</th><th>Power Consumption</th><th>Price</th></tr>"
    $(".removeButton").hide();

    submitForm(tableHeader);

}

function selectRAM() {
    toggleOverlayBuild();
    selectedElement = "Ram";

    itemCategory = "memories";
    tableHeader = "<tr><th>Product Name</th><th>Number Of Sticks</th><th>Power Consumption</th><th>Price</th></tr>"
    $(".removeButton").hide();


    submitForm(tableHeader);

}

function selectMassStorage(number) {
    toggleOverlayBuild();
    selectedElement = "MassStorage";
    massStorageNumber=number;


    itemCategory = "memories";
    tableHeader = "<tr><th>Product Name</th><th>Number Of Memories</th><th>Power Consumption</th><th>Price</th></tr>"

    if(number>=2) {/* funzione che imposta il pulsante di cancellazione dell'elemento*/
        $(".removeButton").show();
        if (number == 2)
            $("#remove").off();
            $("#remove").click(function () {
                massStorage2 = null;
                $("#massStorage2").val("NONE");
                toggleOverlayBuild();
            });
        if (number == 3){
            $("#remove").off();
        $("#remove").click(function () {
                massStorage3 = null;
                $("#massStorage3").val("NONE");
            toggleOverlayBuild();
            })};
    }
else
        $(".removeButton").hide();


    submitForm(tableHeader, number);

}

function selectPcCase() {
    toggleOverlayBuild();
    selectedElement = "PcCase"
    $(".removeButton").hide();

    itemCategory = "cases";
    tableHeader = "<tr><th>Product Name</th><th>Form Factor</th><th>Price</th></tr>"

    submitForm(tableHeader);

}

function selectPsu() {
    toggleOverlayBuild();
    selectedElement = "Psu"

    itemCategory = "psus";
    tableHeader = "<tr><th>Product Name</th><th>Power</th><th>Price</th></tr>"
    $(".removeButton").hide();


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
    let formFactor = formFactorMobo;
    let formFactorEnd = true/* flag che gestisce il loop per la ricerca dei form factor*/
    arrayElements = [];

    do {/*il ciclo viene ripetuto solo con pcCase e Motherboards*/
        formData = "name=" + $("#productName").val().replaceAll(" ", "%20");

        if (compatibilityCheck) {/*form con i controlli*/
            let cpuSocketForm="";
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
            } else $("#massStorageOption").hide();
            console.log(selectedElement)


            if (selectedElement == "MotherBoards") {/* scalare i vari formati di schede compatibili con i case*/
                console.log(formFactorEnd + formFactor)
                if (cpu != undefined)
                    cpuSocketForm = cpu.socket;
                if (formFactorFlag) {
                    if (formFactor.toLowerCase() == "eatx") {
                        formFactor = "atx"
                    } else if (formFactor.toLowerCase() == "atx") {
                        formFactor = "micro-atx"

                    } else if (formFactor.toLowerCase() == "micro-atx") {
                        formFactorEnd = true;/*flag per interrompere il ciclo*/
                        formFactor = "mini-itx"

                    }
                } else {
                    formFactor = (formFactorCase == '') ? "eatx" : formFactorCase;   /*alla prima chiamata di motherboard viene ricercato il form factor stesso del case che lo deve contenere
                    se non è stato ancora selezionato parte dal più grande*/

                    formFactorEnd = false;/*abilito il ciclo do while*/

                    formFactorFlag = true;
                }
            }
            if (selectedElement == "Cpus"&&mobo!=undefined) /* scalare i vari formati di schede compatibili con i case*/
                cpuSocketForm = mobo.cpuSocket;


                if (selectedElement == "PcCase")


                    if (formFactorFlag) {
                        if (formFactor.toLowerCase() == "mini-itx")
                            formFactor = "micro-atx"
                        else if (formFactor.toLowerCase() == "micro-atx") {
                            formFactor = "atx"
                        } else if (formFactor.toLowerCase() == "atx") {
                            formFactor = "eatx"
                            formFactorEnd = true;
                        }
                    } else {
                        formFactorEnd = false;
                        formFactor = (formFactorMobo == '') ? "mini-itx" : formFactorMobo;
                        formFactorFlag = true;
                    }


                if (selectedElement == "Ram") {
                    formData += "&MEMsocket=" + socketRam + "&amountOfMemories=" + slotRamAvailable + "&mType=false";
                }
                formData += "&power=" + powerNeeded + "&id=&formFactor=" + formFactor + "&CPUsocket=" + cpuSocketForm + "&RAMsocket=" + socketRam + "" +
                    "&nRAMSockets=" + slotRamUsed + "&nSATASockets=" + slotSataUsed + "&nNVMESockets=" + slotNVMEUsed + "&requestedItem=" + itemCategory +
                    "&integratedGpu=" + integratedCpu;
            } else {/* inizio formazione del form senza controlli*/

            formFactorEnd = true;
            if (selectedElement == "Ram") {
                massStorageNumber=0;
                formData += "&mType=false&MEMsocket=";
            }
            if (selectedElement == "MassStorage") {
                formData+="&mType=true"
                $("#massStorageOption").show();
                if ($("#sata").prop("checked")) {
                    formData += "&MEMsocket=sata"
                }else formData += "&MEMsocket=nvme"
            }else    $("#massStorageOption").hide();

                formData += "&power=&id=&formFactor=&CPUsocket=&RAMsocket=&nRAMSockets=&nSATASockets=&nNVMESockets=&requestedItem=" + itemCategory +
                    "&integratedGpu=";
            }
            let xhttp = new XMLHttpRequest();

        formData+="&limit="+$("#resultsLimit").val()+"&offset="+$("#offset").val();


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
    row = "<tr class='removable '>" +
        "<td class='productName"+((value.stock<=0)?" outOfStock":"")+"' >" + value.name + "</td>" +
        "<td class='consumption'>" + value.consumption + "</td>" +
        "<td class='price'>" + value.price + "$</td>" +
        "<td><img src='" + value.imagePath + "' alt=''></td>" +
        buttonAdder(value.id, "Gpu") +
        "</tr>";
    $("#searchResultBuild").append(row);
}

function cpuTabler(value) {
    var row;
    row = "<tr class='removable'>" +
        "<td class='productName"+((value.stock<=0)?" outOfStock":"")+"'>" + value.name + "</td>" +
        "<td class='socket'>" + value.socket + "</td>"
    if (value.integratedgpu)
        row += "<td class='integratedGpu'>Yes</td>"
    else row += "<td class='integratedGpu'>No</td>"
    row += "<td class='consumption'>" + value.consumption + "</td>" +
        "<td class='price'>" + value.price + "$</td>" +
        "<td class='inStock'>" + value.stock + "</td>" +
        "<td><img src='" + value.imagePath + "' alt=''></td>" +
        buttonAdder(value.id, "Cpu") +
        "</tr>"
    $("#searchResultBuild").append(row);
}

function memoryTabler(value) {
    var row;
    row = "<tr class='removable'>" +
        "<td class='productName"+((value.stock<=0)?" outOfStock":"")+"'>" + value.name + "</td>" +
        "<td class='amountOfMemories'>" + value.amountMemories + "</td>" +
        "<td class='consumption'>" + value.consumption + "</td>" +
        "<td class='price'>" + value.price + "$</td>" +
        "<td><img src='" + value.imagePath + "' alt=''></td>" +
        buttonAdder(value.id, this.type,massStorageNumber) +
        "</tr>"
    $("#searchResultBuild").append(row);
}

function moboTabler(value) {
    var row;
    row = "<tr class='removable'>" +
        "<td class='productName"+((value.stock<=0)?" outOfStock":"")+"' >" + value.name + "</td>" +
        "<td class='cpuSocket'>" + value.cpuSocket + "</td>" +
        "<td class='ramSocket'>" + value.ramSocket + "</td>" +
        "<td class='ramSlots'>" + value.amountSlotRam + "</td>" +
        "<td class='nvmeSlots'>" + value.amountSlotNvme + "</td>" +
        "<td class='sataSlots'>" + value.amountSlotSata + "</td>" +
        "<td class='formFactor'>" + value.formFactor + "</td>" +
        "<td class='consumption'>" + value.consumption + "</td>" +
        "<td class='price'>" + value.price + "$</td>" +
        "<td><img src='" + value.imagePath + "' alt=''></td>" +
        buttonAdder(value.id, "MotherBoards") +
        "</tr>"
    $("#searchResultBuild").append(row);
}

function pcCaseTabler(value) {
    var row;
    row = "<tr class='removable'>" +
        "<td class='productName "+((value.stock<=0)?" outOfStock":"")+"' >" + value.name + "</td>" +
        "<td class='formFactor'>" + value.formFactor + "</td>" +
        "<td class='price'>" + value.price + "$</td>" +
        "<td><img src='" + value.imagePath + "' alt=''></td>" +
        buttonAdder(value.id, "PcCase") +
        "</tr>"
    $("#searchResultBuild").append(row);
}


function psusTabler(value) {
    var row;
    row = "<tr class='removable'>" +
        "<td class='productName"+((value.stock<=0)?" outOfStock":"")+"' >" + value.name + "</td>" +
        "<td class='power'>" + value.power + "</td>" +
        "<td class='price'>" + value.price + "$</td>" +
        "<td><img src='" + value.imagePath + "' alt=''></td>" +
        buttonAdder(value.id, "Psu") +
        "</tr>"
    $("#searchResultBuild").append(row);
}


function buttonAdder(id, type, number) {
    let buttonForm;
    let action;
    if (type != null&&type!="Ram" && number != 0&&number!=null)
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
    updateSpecification();
    toggleOverlayBuild();

}

function addCpu(id) {
    let elem = arrayElements.find(elem => elem.id == id);
    cpu = elem;
    selectedElementObject = elem;
    $("#cpu").val(elem.name)
    updateSpecification();
    toggleOverlayBuild();
}

function addMotherBoards(id) {
    let elem = arrayElements.find(elem => elem.id == id);
    mobo = elem;
    selectedElementObject = elem;
    $("#mobo").val(elem.name)
    updateSpecification();
    toggleOverlayBuild();
}

function addPcCase(id) {
    let elem = arrayElements.find(elem => elem.id == id);
    pcCase = elem;
    selectedElementObject = elem;
    $("#pcCase").val(elem.name)
    updateSpecification();
    toggleOverlayBuild();
}

function addPsu(id) {
    let elem = arrayElements.find(elem => elem.id == id);
    psu = elem;
    selectedElementObject = elem;
    $("#psu").val(elem.name)
    updateSpecification();
    toggleOverlayBuild();
}

function addRam(id) {
    let elem = arrayElements.find(elem => elem.id == id);
    ram = elem;
    selectedElementObject = elem;
    $("#ram").val(elem.name)
    updateSpecification();
    toggleOverlayBuild();
}

function addMassStorage1(id) {
    let elem = arrayElements.find(elem => elem.id == id);
    massStorage1 = elem;
    selectedElementObject = elem;
    $("#massStorage1").val(elem.name)
    updateSpecification();
    toggleOverlayBuild();
}

function addMassStorage2(id) {
    let elem = arrayElements.find(elem => elem.id == id);
    massStorage2 = elem;
    selectedElementObject = elem;
    $("#massStorage2").val(elem.name)
    updateSpecification();
    toggleOverlayBuild();
}

function addMassStorage3(id) {
    let elem = arrayElements.find(elem => elem.id == id);
    massStorage3 = elem;
    selectedElementObject = elem;
    $("#massStorage3").val(elem.name)
    updateSpecification();

    toggleOverlayBuild();
}

