function changeForm(text) {
    selectedElement = text;
    $("#selectedFormTitle").text(text);
    $("#searchResultItem"); /* without attributes removes all classes*/
    let formHTML;
    let tableHeader;
    let user = false;
    let power = true;
    let name = true;
    $("#insertButton").removeAttr("hidden");
    switch (text) {
        case "Builds":
            tableHeader = "<tr><th>Maker</th><th>Database Id</th><th>MotherBoard</th><th>Gpu</th><th>Cpu</th><th>Ram</th><th>Memories</th></tr>"
            formHTML = "<input type='hidden' id='requestedItem' name='requestedItem' value='builds'>" +
                "<tr><td><label for='CPUname'>CPU Name</label></td><td><input type='text' id='CPUname' name='cpuName'></td></tr>" +
                "<tr><td><label for='GPUname'>GPU Name</label></td><td><input type='text' id='GPUname' name='gpuName'></td></tr>" +
                "<tr><td><label for='PSUname'>PSU Name</label></td><td><input type='text' id='PSUname' name='psuName'></td></tr>" +
                "<tr><td><label for='MOBOname'>MOBO Name</label></td><td><input type='text' id='MOBOname' name='moboName'></td></tr>" +
                "<tr><td><label for='buildType'>Build Type</label></td><td><input type='text' id='buildType' name='type'></td></tr>" +
                "<tr><td><label for='maker'>Maker Name</label></td><td><input type='text' id='maker' name='maker'></td></tr>" +
                "<tr><td><label for='CASEname'>CASE Name</label></td><td><input type='text' id  ='CASEname' name='caseName'></td></tr>" +
                "<tr><td><label for='suggested' >Suggested</label></td>" +
                "<td><input type='radio' id='suggested' name='suggested' value='true'></td></tr>" +

                "<td><label for='Allsug' >All</label></td>" +
                "<td><input type='radio' id='Allsug' name='suggested' checked value=''></td></tr>" +

                "<tr><td><label for='notSuggested'>Not Suggested</label></td>" +
                "<td><input type='radio' id='notSuggested' name='suggested' value='false'></td></tr>"
            name = false;
            break;
        case "Gpus":
            tableHeader = "<tr><th>Product Name</th><th>Database Id</th><th>Power Consumption</th><th>Price</th><th>In Stock</th></tr>"
            formHTML = "<input type='hidden' id='requestedItem' name='requestedItem' value='gpus'>";
            break;
        case "Cpus":
            tableHeader = "<tr><th>Product Name</th><th>Database Id</th><th>Socket</th><th>Integrated Gpu</th><th>Power Consumption</th><th>Price</th><th>In Stock</th></tr>"
            $("#searchResultItem").addClass("cpusTable");
            formHTML = "<input type='hidden' id='requestedItem' name='requestedItem' value='cpus'>" +

                "<tr><td><label for='CPUsocket' >Socket</label></td>" +
                "<td><div class='form'><input type='text' id='CPUsocket' name='CPUsocket' ></td></tr>" +

                "<tr><td><label for='integratedGpu' >Integrated Gpu</label></td>" +
                "<td><div class='form'><input type='radio' id='integratedCPU' name='integratedGpu' value='true'></td></tr>" +
                "<tr><td><label for='noIntegratedGpu' >No Integrated Gpu</label></td>" +
                "<td><div class='form'><input type='radio' id='noIntegratedGpu' name='integratedGpu' value='false'></td></tr>" +
                "<tr><td><label for='ignoreIntegratedGpu' >Ignore Integrated Gpu</label></td>" +
                "<td><input type='radio' id='ignoreIntegratedGpu' name='integratedGpu' value='' checked></td></tr>";
            break;

        case "Memories":
            tableHeader = "<tr><th>Product Name</th><th>Database Id</th><th>Socket</th><th>Memory Type</th><th>Amount Of Memories</th><th>Power Consumption</th><th>Price</th><th>In Stock</th></tr>"

            $("#searchResultItem").addClass("memoriesTable");
            formHTML = "<input type='hidden' id='requestedItem' name='requestedItem' value='memories'>" +

                "<tr><td><label for='MEMsocket' >Memory Socket</label></td>" +
                "<td><input type='text' id='MEMsocket' name='MEMsocket'></td></tr>" +

                "<tr><td><label for='Ram' >Ram</label></td>" +
                "<td><input type='radio' id='Ram' name='mType' value='false'></td></tr>" +

                "<td><label for='All' >All</label></td>" +
                "<td><input type='radio' id='All' name='mType' checked value=''></td></tr>" +

                "<tr><td><label for='MassStorage'>MassStorage</label></td>" +
                "<td class='form'><input type='radio' id='MassStorage' name='mType' value='true'></td></tr>" +

                "<tr><td><label for='amountMemories'>Amount Of Memories</label></td>" +
                "<td><input type='number' id='amountMemories' name='amountOfMemories'></td></tr>";
            break;
        case "Cases":
            $("#searchResultItem").addClass("casesTable");
            tableHeader = "<tr><th>Product Name</th><th>Database Id</th><th>Form Factor</th><th>Price</th><th>In Stock</th></tr>"

            formHTML = "<input type='hidden' id='requestedItem' name='requestedItem' value='cases'>" +

                "<tr><tr><td><label for='formFactor'>Form Factor</label></td>" +
                "<td><select type='number' id='formFActor' name='formFactor'>" +
                "<option value=''>All</option>" +
                "<option value='mini-itx'>Mini-ITX</option>" +
                "<option value='micro-atx'>Micro-ATX</option>" +
                "<option value='atx'>ATX</option>" +
                "</select></td></tr>";
            power = false;
            break;
        case "Psus":
            tableHeader = "<tr><th>Product Name</th><th>Database Id</th><th>Power</th><th>Price</th><th>In Stock</th></tr>"

            $("#searchResultItem").addClass("psusTable");
            formHTML = "<input type='hidden' id='requestedItem' name='requestedItem' value='psus'>" +
                "<tr><td><label for='power'>Power</label></td>" +
                "<td><input type='number' id='power' name='power'></td></tr>";
            break;
        case "Users":
            tableHeader = "<tr><th>Firstname</th><th>Lastname</th><th>Email</th><th>Is Admin</th></tr>"

            $("#searchResultItem").addClass("usersTable");
            formHTML = "<input type='hidden' id='requestedItem' name='requestedItem' value='users'>" +
                "<tr><td><label for='email'>Email</label></td>" +
                "<td><input type='text' id='email' name='email' ></td></tr>" +

                "<tr><td><label for='isAdmin' >Admin</label></td>" +
                "<td><input type='radio' id='isAdmin' name='admin' value='true'></td></tr>" +

                "<td><label for='All' >All</label></td>" +
                "<td><input type='radio' id='All' name='admin' checked value=''></td></tr>" +

                "<tr><td><label for='normalUser'>normalUser</label></td>" +
                "<td><input type='radio' id='normalUser' name='admin' value='false'></td></tr>";

            user = true;
            power = false;
            break;

        case "MotherBoards":
            tableHeader = "<tr><th>Product Name</th><th>Database Id</th><th>Form Factor</th><th>Ram Sockets</th><th>Ram Slots</th><th>NVME Slots</th><th>SATA Slots</th><th>Form Factor</th><th>Power Consumption</th><th>Price</th><th>In Stock</th></tr>"

            $("#searchResultItem").addClass("motherboardsTable");
            formHTML = "<input type='hidden' id='requestedItem' name='requestedItem' value='motherboards'>" +

                "<tr><td><label for='CPUsocket'>CPU Socket</label></td>" +
                "<td><input type='text' id='CPUsocket' name='CPUsocket'></td></tr>" +

                "<tr><td><label for='RAMsocket'>RAM Type</label></td>" +
                "<td><input type='text' id='RAMsocket' name='RAMsocket' ></td></tr>" +

                "<tr><td><label for='nRAMSockets'>RAM Slots</label></td>" +
                "<td><input type='number' id='nRAMSockets' name='nRAMSockets'></td></tr>" +

                "<tr><td><label for='nSATASockets'>SATA Sockets</label></td>" +
                "<td><input type='number' id='nSATASockets' name='nSATASockets'></td></tr>" +

                "<tr><td><label for='nNVMESockets'>NVME Sockets</label></td>" +
                "<td><input type='number' id='nNVMESockets' name='nNVMESockets'></td></tr>" +

                "<tr><td><label for='formFactor'>Form Factor</label></td>" +
                "<td><select id='formFActor' name='formFactor'>" +
                "<option value=''>All</option>" +
                "<option value='mini-itx'>Mini-ITX</option>" +
                "<option value='micro-atx'>Micro-ATX</option>" +
                "<option value='atx'>ATX</option>" +
                "</select></td></tr>";
            break;
        case "Purchases":
            name=false;
            formHTML="<input type='hidden' id='requestedItem' name='requestedItem' value='purchases'>"+
                "<tr><td><label for='startingDate'>Starting Date</label></td>"+
                "<td><input type='date' id='startingDate' name='startingDate'></td></tr>"+
            "<tr><td><label htmlFor='endingDate' >Starting Date</label></td>"+
                "<td><input type='date' id='endingDate' name='endingDate'></td></tr>"+
        "<tr><td><label for='email'>Email</label></td>" +
            "<td><input type='text' id='email' name='email' ></td></tr>" ;
            break;

    }


    if (name)
        formHTML += "<tr><td><label for='name'>Name</label></td><td><input type='text' id='name' name='name'></td></tr>";

    $("#searchFormContainer").html(formHTML);

    $("#searchResultItem").html(tableHeader);
    submitForm();
}


function prepareFormUpdate(item) {
    let formHTML;

    switch (selectedElement) {
        case"Gpus":
            formHTML = ' <input type="hidden" id="requestedItemUpdate" name="requestedItem" value="gpus">' +
                '<input type="hidden" name="id" value="' + item.id + '" >' +

                '<tr><td><label for="id">DataBase Id</label></td>' +
                '<td>'+ item.id + '</td></tr>' +

                '<tr><td><label for="productName">Product Name</label></td>' +
                '<td><input type="text" id="productName" name="name" value="' + item.name + '" onkeyup="checkProductName()" maxlength="100">' +
                '<span id="name-alert" class="alert-info " hidden> Product Already Present</span></td></tr>' +

                '<tr><td><label for="consumption">Consumption</label></td>' +
                '<td><input type="number" name="consumption" id="consumption" value="' + item.consumption + '"></td></tr>' +

                '<tr><td><label for="price">Price</label></td>' +
                '<td><input type="number" step="0.01" name="price" value="' + item.price + '"></td></tr>' +

                '<tr><td><label for="inStock">In Stock</label></td>' +
                '<td><input type="number" id="inStock" name="stock" value="' + item.stock + '"></td></tr>' +


                '<tr><td><img src="' + item.imagePath + '"></td></tr>'
            break;
        case"Cpus":
            let integrated = (item.integratedGpu) ? 'checked' : '';
            let noIntegrated = (!item.integratedGpu) ? 'checked' : '';
            formHTML =
                '<input type="hidden" id="requestedItemUpdate" name="requestedItem" value="cpus">' +

                '<tr><td><label for="id">DataBase Id</label></td>' +
                '<td><input type="text" name="id" value="' + item.id + '" disabled></td></tr>' +

                '<tr><td><label for="productName">Product Name</label></td>' +
                '<td><input name="name" type="text" id="productName" value="' + item.name + '" onkeyup="checkProductName()" maxlength="100">' +
                '<span id="name-alert" class="alert-info " hidden> Product Already Present</span></td></tr>' +

                '<tr><td><label for="CPUsocket">Socket</label></td>' +
                '<td><input type="text" id="CPUsocket" name="CPUsocket" value="' + item.socket + '" maxlength="10"></td></tr>' +

                '<tr><td><label for="integratedGpu">Integrated Gpu</label></td>' +
                '<td><input type="radio" id="integratedGpu" name="integratedGpu" value="true"' + integrated + '></td></tr>' +

                '<tr><td><label for="noIntegratedGpu">No Integrated Gpu</label></td>' +
                '<td><input type="radio" id="noIntegratedGpu" name="integratedGpu" value="false"' + noIntegrated + '></td></tr>' +

                '<tr><td><label for="consumption">Consumption</label></td>' +
                '<td><input type="number" name="consumption" id="consumption" value="' + item.consumption + '"></td></tr>' +

                '<tr><td><label for="price">Price</label></td>' +
                '<td><input type="number" step="0.01" name="price" value="' + item.price + '"></td></tr>' +

                '<tr><td><label for="inStock">In Stock</label></td>' +
                '<td><input type="number" id="inStock" name="stock" value="' + item.stock + '"></td></tr>' +

                '<tr><img src="' + item.imagePath + '"></td></tr>';
            break;

        case"Memories":
            let ram = (!item.mType) ? 'checked' : '';
            let massStorage = (item.mType) ? 'checked' : '';
            formHTML =
                ' <input type="hidden" id="requestedItemUpdate" name="requestedItem" value="memories">' +

                '<tr><td><label for="id">DataBase Id</label></td>' +
                '<td><input type="text" name="id" value="' + item.id + '" disabled></td></tr>' +

                '<tr><td><label for="productName">Product Name</label></td>' +
                '<td><input name="name" type="text" id="productName" value="' + item.name + '" onkeyup="checkProductName()" maxlength="100">' +
                '<span id="name-alert" class="alert-info " hidden> Product Already Present</span></td></tr>' +

                ' <tr><td><label for="MEMsocket">Memory Socket</label></td>' +
                '<td><input type="text" id="MEMsocket" name="MEMsocket" value="' + item.socket + '" maxlength="100"></td></tr>' +

                '<tr><td><label for="Ram">Ram</label></td>' +
                '<td><input type="radio" id="Ram" name="mType" value="false"' + Ram + '"></td></tr>' +

                '<tr><td><label for="MassStorage">MassStorage</label></td>' +
                '<td class="form"><input type="radio" id="MassStorage" name="mType" value="true"' + massStorage + '</td></tr>' +

                '<tr><td><label for="amountMemories">Amount Of Memories</label></td>' +
                '<td><input type="number" id="amountMemories" name="amountOfMemories" value=' + item.amountMemories + '></td></tr>' +


                '<tr><td><label for="consumption">Consumption</label></td>' +
                '<td><input type="number" name="consumption" id="consumption" value="' + item.consumption + '"></td></tr>' +

                '<tr><td><label for="price">Price</label></td>' +
                '<td><input type="number" step="0.01" name="price"  value="' + item.price + '"></td></tr>' +

                '<tr><td><label for="inStock">In Stock</label></td>' +
                '<td><input type="number" id="inStock" name="stock" value="' + item.stock + '"></td></tr>' +


                '<td><img src="' + item.imagePath + '"></td></tr>';
            break;
        case"Cases":
            let miniitx = (item.formFactor == "mini-itx") ? 'selected' : '';
            let microatx = (item.formFactor == "micro-ATX") ? 'selected' : '';
            let atx = (item.formFactor == "ATX") ? 'selected' : '';
            formHTML = ' <input type="hidden" id="requestedItemUpdate" name="requestedItem" value="cases">' +

                '<tr><td><label for="id">DataBase Id</label></td>' +
                '<td><input type="text" name="id" value="' + item.id + '"   ></td></tr>' +

                '<tr><td><label for="productName">Product Name</label></td>' +
                '<td><input type="text" id="productName" name="name" value="' + item.name + '" onkeyup="checkProductName()" >' +
                '<span id="name-alert" class="alert-info " hidden> Product Already Present</span></td></tr>' +

                '<tr><td><label for="formFactor">Form Factor</label></td>' +
                '<td><select id="formFActor" name="formFactor">' +
                '<option value="mini-itx"' + miniitx + '>Mini-ITX</option>' +
                " <option value='micro-ATX' " + microatx + ">Micro-ATX</option>" +
                "<option value='ATX'" + atx + ">ATX</option></select></td></tr>" +


                '<tr><td><label for="price">Price</label></td>' +
                '<td><input type="number" step="0.01" name="price" value="' + item.price + '"></td></tr>' +

                '<tr><td><label for="inStock">In Stock</label></td>' +
                '<td><input type="number" id="inStock" name="stock" value="' + item.stock + '"></td></tr>' +


                '<td><img src="' + item.imagePath + '"></td></tr>';
            break;
        case"MotherBoards":
            let miniitxm = (item.formFactor == "mini-itx") ? 'selected' : '';
            let microatxm = (item.formFactor == "micro-atx") ? 'selected' : '';
            let atxm = (item.formFactor == "atx") ? 'selected' : '';
            formHTML = ' <input type="hidden" id="requestedItemUpdate" name="requestedItem" value="motherboards">' +

                '<tr><td><label for="id">DataBase Id</label></td>' +
                '<td><input type="text" name="id" value="' + item.id + '" ></td></tr>' +

                '<tr><td><label for="productName">Product Name</label></td>' +
                '<td><input type="text" id="productName" name="name" value="' + item.name + '" onkeyup="checkProductName()">' +
                '<span id="name-alert" class="alert-info " hidden> Product Already Present</span></td></tr>' +

                "<tr><td><label for='CPUsocket'>CPU Socket</label></td>" +
                " <td><input type='text' id='CPUsocket' name='CPUsocket' value='" + item.cpuSocket + "'></td></tr>" +

                "<tr><td><label for='RAMsocket'>RAM Socket</label></td>" +
                " <td><input type='text' id='RAMsocket' name='RAMsocket' value='" + item.ramSocket + "'></td></tr>" +

                " <tr><td><label for='nRAMSockets'>RAM Slots</label></td>" +
                "  <td><input type='number' id='nRAMSockets' name='nRAMSockets' value='" + item.amountSlotRam + "' ></td></tr>" +

                " <tr><td><label for='nSATASockets'>SATA Slots</label></td>" +
                "  <td><input type='number' id='nSATASockets' name='nSATASockets' value='" + item.amountSlotSata + "' ></td></tr>" +

                " <tr><td><label for='nNVMESockets'>NVME Slots</label></td>" +
                "  <td><input type='number' id='nNVMESockets' name='nNVMESockets' value='" + item.amountSlotNvme + "' ></td></tr>" +

                '<tr><td><label for="formFactor">Form Factor</label></td>' +
                '<td><select id="formFActor" name="formFactor">' +
                '<option value="mini-itx"' + miniitxm + '>Mini-ITX</option>' +
                " <option value='micro-atx' " + microatxm + ">Micro-ATX</option>" +
                "<option value='atx'" + atxm + ">ATX</option></select></td></tr>" +

                '<tr><td><label for="consumption">Consumption</label></td>' +
                '<td><input type="number" id="consumption" name="consumption" value="' + item.consumption + '"></td></tr>' +

                '<tr><td><label for="price">Price</label></td>' +
                '<td><input type="number" step="0.01" name="price" value="' + item.price + '"></td></tr>' +

                '<tr><td><label for="inStock">In Stock</label></td>' +
                '<td><input type="number" id="inStock" name="stock" value="' + item.stock + '"></td></tr>' +


                '<td><img src="' + item.imagePath + '"></td></tr>';
            break
        case"Psus":
            formHTML = ' <input type="hidden" id="requestedItemUpdate" name="requestedItem" value="psus">' +

                '<tr><td><label for="id">DataBase Id</label></td>' +
                '<td><input type="text" name="id" name="name" value="' + item.id + '" disabled></td></tr>' +

                '<tr><td><label for="productName">Product Name</label></td>' +
                '<td><input type="text" id="productName" value="' + item.name + '" onkeyup="checkProductName()">' +
                '<span id="name-alert" class="alert-info " hidden> Product Already Present</span></td></tr>' +

                '<tr><td><label for="power">Power</label></td>' +
                '<td><input type="text" id="power" name="power" value="' + item.power + '"></td></tr>' +

                '<tr><td><label for="price">Price</label></td>' +
                '<td><input type="number" step="0.01" name="price" value="' + item.price + '"></td></tr>' +

                '<tr><td><label for="inStock">In Stock</label></td>' +
                '<td><input type="number" id="inStock" name="stock" value="' + item.stock + '"></td></tr>' +

                '<td><img src="' + item.imagePath + '"></td></tr>';
            break;

        case"Users":
            let admin = (item.admin) ? 'checked' : '';
            formHTML = ' <input type="hidden" id="requestedItemUpdate" name="requestedItem" value="users">' +

                '<tr><td><label for="email">User Email</label></td>' +
                '<td><input type="text" name="email" value="' + item.email + '" disabled></td></tr>' +

                '<tr><td><label for="firstName">Firstname</label></td>' +
                '<td><input type="text" name="firstName" value="' + item.firstName + '" ></td></tr>' +

                '<tr><td><label for="lastName">Lastname</label></td>' +
                '<td><input type="text" name="lastName" value="' + item.lastName + '" ></td></tr>' +

                '<tr><td><label for="admin">Admin</label></td>' +
                '<td><input type="checkbox" id="admin" name="admin" value="true"' + admin + '></td></tr>';
            break;

    }
    let buttonHTML='<tr><td><input type="radio" name="option" value="delete" class="btn btn-danger" >Delete</input></td>' +
        '<td><input id="saveChanges" type="radio" name="option" value="update"  class="btn btn-success" >Save Changes</input></td>' +
        '<td><input id="submitDBUpdate" type="submit" class="btn active"></td></tr>';
    console.log(formHTML);
    $("#imageInput").html('<input type="file" id="image" name="image">');
    $("#updateTitle").text("Update Element");
    $("#updateFormBox").html(formHTML);
    $("#buttonSpace").html(buttonHTML)
    toggleOverlayModify();
}

function prepareFormInsert() {
    let user=false;
    let formHTML;

    switch (selectedElement) {
        case"Gpus":
            formHTML = ' <input type="hidden" id="requestedItemUpdate" name="requestedItem" value="gpus">' +
                '<input type="hidden" name="option" value="insert">' +
                '<input type="hidden" id="id" name="id" value="-1">' +

                '<tr><td><label for="productName">Product Name</label></td>' +
                '<td><input type="text" id="productName" name="name"  onkeyup="checkProductName()">' +
                '<span id="name-alert" class="alert-info " hidden> Product Already Present</span></td></tr>' +

                '<tr><td><label for="consumption">Consumption</label></td>' +
                '<td><input type="number" name="consumption" id="consumption" ></td></tr>' +

                '<tr><td><label for="price">Price</label></td>' +
                '<td><input type="number" step="0.01" name="price"></td></tr>' +

                '<tr><td><label for="inStock">In Stock</label></td>' +
                '<td><input type="number" id="inStock" name="stock" ></td></tr>'


            break;
        case"Cpus":
            formHTML =
                '<input type="hidden" id="requestedItemUpdate" name="requestedItem" value="cpus">' +
                '<input type="hidden" name="option" value="insert">' +
                '<input type="hidden" id="id" name="id" value="-1">' +


                '<tr><td><label for="productName">Product Name</label></td>' +
                '<td><input name="name" type="text" id="productName" onkeyup="checkProductName()" >' +
                '<span id="name-alert" class="alert-info " hidden> Product Already Present</span></td></tr>' +

                '<tr><td><label for="CPUsocket">Socket</label></td>' +
                '<td><input type="text" id="CPUsocket" name="CPUsocket"></td></tr>' +

                '<tr><td><label for="integratedGpu">Integrated Gpu</label></td>' +
                '<td><input type="radio" id="integratedGpu" name="integratedGpu" ></td></tr>' +

                '<tr><td><label for="noIntegratedGpu">No Integrated Gpu</label></td>' +
                '<td><input type="radio" id="noIntegratedGpu" name="integratedGpu" value="false"></td></tr>' +

                '<tr><td><label for="consumption">Consumption</label></td>' +
                '<td><input type="number" name="consumption" id="consumption"></td></tr>' +

                '<tr><td><label for="price">Price</label></td>' +
                '<td><input type="number" step="0.01" name="price" ></td></tr>' +

                '<tr><td><label for="inStock">In Stock</label></td>' +
                '<td><input type="number" id="inStock" name="stock"></td></tr>'


            break;

        case"Memories":

            formHTML =
                ' <input type="hidden" id="requestedItemUpdate" name="requestedItem" value="memories">' +
                '<input type="hidden" name="option" value="insert">' +
                '<input type="hidden" id="id" name="id" value="-1">' +


                '<tr><td><label for="productName">Product Name</label></td>' +
                '<td><input name="name" type="text" id="productName" onkeyup="checkProductName()" >' +
                '<span id="name-alert" class="alert-info " hidden> Product Already Present</span></td></tr>' +

                ' <tr><td><label for="MEMsocket">Memory Socket</label></td>' +
                '<td><input type="text" id="MEMsocket" name="MEMsocket"></td></tr>' +

                '<tr><td><label for="Ram">Ram</label></td>' +
                '<td><input type="radio" id="Ram" name="mType" value="false"></td></tr>' +

                '<tr><td><label for="MassStorage">MassStorage</label></td>' +
                '<td class="form"><input type="radio" id="MassStorage" name="mType" value="true"</td></tr>' +

                '<tr><td><label for="amountMemories">Amount Of Memories</label></td>' +
                '<td><input type="number" id="amountMemories" name="amountOfMemories" ></td></tr>' +


                '<tr><td><label for="consumption">Consumption</label></td>' +
                '<td><input type="number" name="consumption" id="consumption" ></td></tr>' +

                '<tr><td><label for="price">Price</label></td>' +
                '<td><input type="number" step="0.01" name="price" ></td></tr>' +

                '<tr><td><label for="inStock">In Stock</label></td>' +
                '<td><input type="number" id="inStock" name="stock"></td></tr>'


            break;
        case"Cases":
            formHTML = ' <input type="hidden" id="requestedItemUpdate" name="requestedItem" value="cases">' +
                '<input type="hidden" name="option" value="insert">' +
                '<input type="hidden" id="id" name="id" value="-1">' +

                '<tr><td><label for="productName">Product Name</label></td>' +
                '<td><input type="text" id="productName" name="name" onkeyup="checkProductName()" >' +
                '<span id="name-alert" class="alert-info " hidden> Product Already Present</span></td></tr>' +

                '<tr><td><label for="formFactor">Form Factor</label></td>' +
                '<td><select id="formFActor" name="formFactor">' +
                '<option value="mini-itx">Mini-ITX</option>' +
                " <option value='micro-ATX' >Micro-ATX</option>" +
                "<option value='ATX'>ATX</option></select></td></tr>" +

                '<tr><td><label for="consumption">Consumption</label></td>' +
                '<td><input type="number" id="consumption" name="consumption"></td></tr>' +

                '<tr><td><label for="price">Price</label></td>' +
                '<td><input type="number" step="0.01" name="price" ></td></tr>' +

                '<tr><td><label for="inStock">In Stock</label></td>' +
                '<td><input type="number" id="inStock" name="stock" ></td></tr>'


            break;
        case"MotherBoards":

            formHTML = ' <input type="hidden" id="requestedItemUpdate" name="requestedItem" value="motherboards">' +
                '<input type="hidden" name="option" value="insert">' +
                '<input type="hidden" id="id" name="id" value="-1">' +

                '<tr><td><label for="productName">Product Name</label></td>' +
                '<td><input type="text" id="productName" name="name" onkeyup="checkProductName()">' +
                '<span id="name-alert" class="alert-info " hidden> Product Already Present</span></td></tr>' +

                "<tr><td><label for='CPUsocket'>CPU Socket</label></td>" +
                " <td><input type='text' id='CPUsocket' name='CPUsocket' ></td></tr>" +

                "<tr><td><label for='RAMsocket'>RAM Socket</label></td>" +
                " <td><input type='text' id='RAMsocket' name='RAMsocket' ></td></tr>" +

                " <tr><td><label for='nRAMSockets'>RAM Slots</label></td>" +
                "  <td><input type='number' id='nRAMSockets' name='nRAMSockets' ></td></tr>" +

                " <tr><td><label for='nSATASockets'>SATA Slots</label></td>" +
                "  <td><input type='number' id='nSATASockets' name='nSATASockets' ></td></tr>" +

                " <tr><td><label for='nNVMESockets'>NVME Slots</label></td>" +
                "  <td><input type='number' id='nNVMESockets' name='nNVMESockets' ></td></tr>" +

                '<tr><td><label for="formFactor">Form Factor</label></td>' +
                '<td><select id="formFActor" name="formFactor">' +
                '<option value="mini-itx">Mini-ITX</option>' +
                " <option value='micro-atx'>Micro-ATX</option>" +
                "<option value='atx'>ATX</option></select></td></tr>" +

                '<tr><td><label for="consumption">Consumption</label></td>' +
                '<td><input type="number" id="consumption" name="consumption"></td></tr>' +

                '<tr><td><label for="price">Price</label></td>' +
                '<td><input type="number" step="0.01" name="price" ></td></tr>' +

                '<tr><td><label for="inStock">In Stock</label></td>' +
                '<td><input type="number" id="inStock" name="stock"></td></tr>'


            break
        case"Psus":
            formHTML = ' <input type="hidden" id="requestedItemUpdate" name="requestedItem" value="psus">' +
                '<input type="hidden" name="option" value="insert">' +
                '<input type="hidden" id="id" name="id" value="-1">' +

                '<tr><td><label for="productName">Product Name</label></td>' +
                '<td><input type="text" id="productName"  onkeyup="checkProductName()">' +
                '<span id="name-alert" class="alert-info " hidden> Product Already Present</span></td></tr>' +

                '<tr><td><label for="power">Power</label></td>' +
                '<td><input type="text" id="power" name="power" ></td></tr>' +

                '<tr><td><label for="price">Price</label></td>' +
                '<td><input type="number" step="0.01" name="price" ></td></tr>' +

                '<tr><td><label for="inStock">In Stock</label></td>' +
                '<td><input type="number" id="inStock" name="stock" ></td></tr>'


            break;

        case"Users":
            formHTML = ' <input type="hidden" id="requestedItemUpdate" name="requestedItem" value="users">' +
                '<input type="hidden" name="option" value="insert">' +
                '<input type="hidden" id="id" name="id" value="-1">' +

                '<tr><td><label for="email">User Email</label></td>' +
                '<td><input type="email" name="email" id="email" onfocusout="existingEmail()" required>' +
                '<span id="email-alert" class="alert-info " hidden> Incorrect Email</span></td></tr>' +


                '<tr><td><label for="admin">Admin</label></td>' +
                '<td><input type="checkbox" id="admin" name="admin" required></td></tr>'
            user=true;
            break;

    }

    let buttonHTML='<tr><td><input id="submitDBUpdate" type="submit" class="btn btn-success" value="Insert Element"></td></tr>';
    if(user){
        $("#updateForm").attr("action",".\/modifyDB");
    }else{
        $("#updateForm").attr("action",".\/modifyDB");
    }

    console.log(formHTML);
    $("#imageInput").html('<input type="file" id="image" name="image">');
    $("#updateTitle").text("Create Element");
    $("#updateFormBox").html(formHTML);
    $("#buttonSpace").html(buttonHTML)
    toggleOverlayModify();
}