function userTabler(value) {
    var row;
    row = "<tr class='removable'>" +
        "<td class='firstname'>" + value.firstName + "</td>" +
        "<td class='lastname'>" + value.lastName + "</td>" +
        "<td class='email'>" + value.email + "</td>";
    row += checkAdminAdder(value) + "</tr>";
    $("#searchResultItem").append(row);
}
function checkAdminAdder(value){
    let checked= (value.admin)?'checked':'';
    let email=value.email.replaceAll(".","\\\\.");/*una coppia di slash per questa stinga*/
    email=email.replaceAll("@","\\\\@");
    let button="<td><form onclick=\"adminModifier('"+email+"')\">" +
        /* "<input type=\"hidden\" name=\"admin\" value=\""+value.email+"\"   >" +*/
        "<input id=\""+value.email+"\" type=\"checkbox\" name=\"admin\" value=\"true\" "+checked+ ">" +
        "</form></td>";
    return button;
}
function adminModifier(email){

    let formData="email="+$("#"+email).attr("id").replaceAll("@","%40").replaceAll(".","%2E")/*La seconda coppia di slash serve per cercare l'elemento*/
    console.log(formData)

    /* email formattata per il form*/
    formData+=($("#"+email).prop("checked"))?"&admin=true":"&admin=false";
    formData+="&requestedItem=users&option=update";
    console.log(formData);
    updateAdmin(formData);
}

function updateAdmin(formData){
    $.ajax({
        url: "./admin/modifyDB",
        type: 'POST',
        data: formData,
        beforeSend: function (x) {
            x.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        },
        success: function (data) {
            console.log(data);
            if(data=="true")
                createToast("Success","Change Successful")

        },
        failed:function(data){
            createToast("Error","Cannot Change Admin Status")
        },
        cache: false,
        contentType: false,
        processData: false
    });


}


function gpuTabler(value) {
    var row;
    row = "<tr class='removable'>" +
        "<td class='productName' >" + value.name + "</td>" +
        "<td class='databaseId'>" + value.id + "</td>" +
        "<td class='consumption'>" + value.consumption + "</td>" +
        "<td class='price'>" + value.price + "$</td>" +
        "<td class='inStock'>" + value.stock + "</td>" +
        "<td><img src='./images/"+value.imagePath+"'></td>" +
        buttonAdder(value.id) +
        "</tr>";
    $("#searchResultItem").append(row);
}

function cpuTabler(value) {
    var row;
    row = "<tr class='removable'>" +
        "<td class='productName'>" + value.name + "</td>" +
        "<td class='databaseId'>" + value.id + "</td>" +
        "<td class='socket'>" + value.socket + "</td>"
    if (value.integratedgpu)
        row += "<td class='integratedGpu'>Yes</td>"
    else row += "<td class='integratedGpu'>No</td>"

    row += "<td class='consumption'>" + value.consumption + "</td>" +
        "<td class='price'>" + value.price + "$</td>" +
        "<td class='inStock'>" + value.stock + "</td>" +
        "<td><img src='./images/"+value.imagePath+"'></td>" +
        buttonAdder(value.id) +
        "</tr>"
    $("#searchResultItem").append(row);
}

function memoryTabler(value) {
    var row;
    row = "<tr class='removable'>" +
        "<td class='productName'>" + value.name + "</td>" +
        "<td class='databaseId'>" + value.id + "</td>" +
        "<td class='socket'>" + value.socket + "</td>";
    if (value.mType)
        row += "<td class='memoryType'>Mass Storage</td>"
    else row += "<td class='memoryType'>Ram</td>"

    row += "<td class='amountOfMemories'>" + value.amountMemories + "</td>" +
        "<td class='consumption'>" + value.consumption + "</td>" +
        "<td class='price'>" + value.price + "$</td>" +
        "<td class='inStock'>" + value.stock + "</td>" +
        "<td><img src='./images/"+value.imagePath+"'></td>" +
        buttonAdder(value.id) +
        "</tr>"
    $("#searchResultItem").append(row);
}

function moboTabler(value) {
    var row;
    row = "<tr class='removable'>" +
        "<td class='productName' >" + value.name + "</td>" +
        "<td class='databaseId'>" + value.id + "</td>" +
        "<td class='cpuSocket'>" + value.cpuSocket + "</td>" +
        "<td class='ramSocket'>" + value.ramSocket + "</td>" +
        "<td class='ramSlots'>" + value.amountSlotRam + "</td>" +
        "<td class='nvmeSlots'>" + value.amountSlotNvme + "</td>" +
        "<td class='sataSlots'>" + value.amountSlotSata + "</td>" +
        "<td class='formFactor'>" + value.formFactor + "</td>" +
        "<td class='consumption'>" + value.consumption + "</td>" +
        "<td class='price'>" + value.price + "$</td>" +
        "<td class='inStock'>" + value.stock + "</td>" +
        "<td><img src='./images/"+value.imagePath+"'></td>" +
        buttonAdder(value.id) +
        "</tr>"
    $("#searchResultItem").append(row);
}

function pcCaseTabler(value) {
    var row;
    row = "<tr class='removable'>" +
        "<td class='productName' >" + value.name + "</td>" +
        "<td class='databaseId'>" + value.id + "</td>" +
        "<td class='formFactor'>" + value.formFactor + "</td>" +
        "<td class='price'>" + value.price + "$</td>" +
        "<td class='inStock'>" + value.stock + "</td>" +
        "<td><img src='./images/"+value.imagePath+"'></td>" +
        buttonAdder(value.id) +
        "</tr>"
    $("#searchResultItem").append(row);
}

function buildTabler(value) {
    var row;
    row = "<tr class='removable'>" +
        "<td class='maker'>" + value.maker + "</td>" +
        "<td class='databaseId'>" + value.id + "</td>" +
        "<td class='moboName'>" + value.mobo + "</td>" +
        "<td  class='gpuName'>" + value.gpu + "</td>" +
        "<td class='cpuName'>" + value.cpu + "</td>" +
        "<td class='caseName'>" + value.pcCase + "</td>" +
        "<td class='memoriesName'>";
    console.log(value.memories);
    for (let i in value.memories) {
        row += value.memories[i] + "<br>";
    }
    row += "</td>";
    if (value.suggested)
        row += "<td class='isSuggested'>Yes</td>"
    else row += "<td class='isSuggested'>No</td>"

    row += "<td class='buildType'>" + value.type + "</td>" +

        buttonAdderBuild(value.id) +
        "</tr>"
    $("#searchResultItem").append(row);
}

function psusTabler(value) {
    var row;
    row = "<tr class='removable'>" +
        "<td class='productName' >" + value.name + "</td>" +
        "<td class='databaseId'>" + value.id + "</td>" +
        "<td class='power'>" + value.power + "</td>" +
        "<td class='price'>" + value.price + "$</td>" +
        "<td class='inStock'>" + value.stock + "</td>" +
        "<td><img src='./images/"+value.imagePath+"'></td>" +
        buttonAdder(value.id) +
        "</tr>"
    $("#searchResultItem").append(row);
}
function purchaseTabler(value){
    row='<tr class="removable">'+
        '<td className="creationDate">'+value.creationDate+'</td>'+
       ' <td className="email">'+value.email+'</td>'+
        '<td className="country">'+value.country+'</td>'+
        '<td className="cap">'+value.cap+'</td>'+
       ' <td className="city">'+value.city+'</td>'+
       ' <td className="address">'+value.address+'</td>'+
       ' <td className="cellphonenumber">'+value.cellphonenumber+'</td>'+
        '<td className="idbuild">'+value.idbuild+'</td>'+
        '<td className="id">'+value.id+'</td>'+
        '</tr>'
    $("#searchResultItem").append(row);
}

function buttonAdderBuild(id) {
    let buttonForm;
    let requestedItem = $("#requestedItem").attr("value");
    buttonForm = "<td><form id='" + id + "' action='/MYOPSite_war_exploded/showBuild'>" +
        "<input type='hidden' name='id' value='" + id + "'>" +
        "<input type='submit' class='btn active' value='Modify'></form></td>"+
        "<td><form id='" + id + "' action='/MYOPSite_war_exploded/deleteBuild'>" +
        "<input type='hidden' name='id' value='" + id + "'>" +
        "<input type='submit' class='btn btn-danger' value='Delete'></form></td>";
    return buttonForm;
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