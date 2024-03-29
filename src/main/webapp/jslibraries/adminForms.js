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
            tableHeader = "<thead><tr><th>Maker</th><th>Database Id</th><th>MotherBoard</th><th>Gpu</th><th>Cpu</th><th>Ram</th><th>Memories</th></tr></thead>"
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
            tableHeader = "<thead><tr><th>Product Name</th><th>Database Id</th><th>Power Consumption</th><th>Price</th><th>In Stock</th></tr></thead>"
            formHTML = "<input type='hidden' id='requestedItem' name='requestedItem' value='gpus'>";
            break;
        case "Cpus":
            tableHeader = "<thead><tr><th>Product Name</th><th>Database Id</th><th>Socket</th><th>Integrated Gpu</th><th>Power Consumption</th><th>Price</th><th>In Stock</th></tr></thead>"
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
            tableHeader = "<thead><tr><th>Product Name</th><th>Database Id</th><th>Socket</th><th>Memory Type</th><th>Amount Of Memories</th><th>Power Consumption</th><th>Price</th><th>In Stock</th></tr></thead>"

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
            tableHeader = "<thead><tr><th>Product Name</th><th>Database Id</th><th>Form Factor</th><th>Price</th><th>In Stock</th></tr></thead>"

            formHTML = "<input type='hidden' id='requestedItem' name='requestedItem' value='cases'>" +

                "<tr><tr><td><label for='formFactor'>Form Factor</label></td>" +
                "<td><select type='number' id='formFActor' name='formFactor'>" +
                "<option value=''>All</option>" +
                "<option value='mini-itx'>Mini-ITX</option>" +
                "<option value='micro-atx'>Micro-ATX</option>" +
                "<option value='atx'>ATX</option>" +
                "<option value='eatx'>EATX</option>" +
                "</select></td></tr>";
            power = false;
            break;
        case "Psus":
            tableHeader = "<thead><tr><th>Product Name</th><th>Database Id</th><th>Power</th><th>Price</th><th>In Stock</th></tr></thead>"

            $("#searchResultItem").addClass("psusTable");
            formHTML = "<input type='hidden' id='requestedItem' name='requestedItem' value='psus'>" +
                "<tr><td><label for='power'>Power</label></td>" +
                "<td><input type='number' id='power' name='power'></td></tr>";
            break;
        case "Users":
            $("#insertButton").attr("hidden","true");
            tableHeader = "<thead><tr><th>Firstname</th><th>Lastname</th><th>Email</th><th>Is Admin</th></tr>"

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
            tableHeader = "<thead><tr><th>Product Name</th><th>Database Id</th><th>Form Factor</th><th>Ram Sockets</th><th>Ram Slots</th><th>NVME Slots</th><th>SATA Slots</th><th>Form Factor</th><th>Power Consumption</th><th>Price</th><th>In Stock</th></tr></thead>"

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
            tableHeader = "<thead><tr><th>Creation Date</th><th>User</th><th>Country</th><th>Postal Code</th><th>City</th><th>Address</th><th>Id Build</th><th>Form Factor</th><th>Purchase Id</th></tr></thead>";

            name=false;
            formHTML="<input type='hidden' id='requestedItem' name='requestedItem' value='purchases'>"+
                "<tr><td><label for='startingDate'>Starting Date</label></td>"+
                "<td><input type='date' id='startingDate' name='startingDate'></td></tr>"+
            "<tr><td><label htmlFor='endingDate' >Starting Date</label></td>"+
                "<td><input type='date' id='endingDate' name='endingDate'></td></tr>"+
        "<tr><td><label for='email'>Email</label></td>" +
            "<td><input type='text' id='email' name='email' ></td></tr>" ;
            break;
        case "Countries":
            tableHeader = "<thead><tr><th>Country Id</th><th>Country Label</th></tr></thead>";
            formHTML="<input type='hidden' id='requestedItem' name='requestedItem' value='countries'>";
            name=false;

    }


    if (name)
        formHTML += "<tr><td><label for='name'>Name</label></td><td><input type='text' id='name' name='name'></td></tr>";

    formHTML+='<tr><td><label for="resultsLimit">Results Per Page</label></td>' +
        '<td><input type="number" name="limit" max="50" id="resultsLimit" value="25"></td></tr>' +
        '<tr><td><label for="offset">Page</label></td>' +
        '<td><input type="number" name="offset" id="offset" onclick="submitForm()" value="1"></td></tr>';

    $("#searchFormContainer").html(formHTML);

    $("#searchResultItem").html(tableHeader);
    submitForm();
}


function prepareFormUpdate(item) {
    let formHTML;
    let image=false;

    switch (selectedElement) {
        case"Gpus":
            image=true;
            formHTML = ' <input type="hidden" id="requestedItemUpdate" name="requestedItem" value="gpus">' +
                '<input type="hidden" id="id" name="id" value="' + item.id + '" >' +

                '<tr><td><label for="id">DataBase Id</label></td>' +
                '<td>'+ item.id + '</td></tr>' +

                '<tr><td><label for="productName">Product Name</label></td>' +
                '<td><input type="text" id="productName" name="name" value="' + item.name + '" onkeyup="checkProductName()" maxlength="100"required>' +
                '<span id="name-alert" class="alert-info " hidden> Product Already Present</span></td></tr>' +

                '<tr><td><label for="consumption">Consumption</label></td>' +
                '<td><input type="number" name="consumption" id="consumption" value="' + item.consumption + '"required></td></tr>' +

                '<tr><td><label for="price">Price</label></td>' +
                '<td><input type="number" step="0.01" name="price" value="' + item.price + '"required></td></tr>' +

                '<tr><td><label for="inStock">In Stock</label></td>' +
                '<td><input type="number" id="inStock" name="stock" value="' + item.stock + '"required></td></tr>' +


                '<tr><td></td><td><img src="' + item.imagePath + '" alt=""></td></tr>'
            break;
        case"Cpus":
            image=true;
            let integrated = (item.integratedGpu) ? 'checked' : '';
            let noIntegrated = (!item.integratedGpu) ? 'checked' : '';
            formHTML =
                '<input type="hidden" id="requestedItemUpdate" name="requestedItem" value="cpus">' +

                '<tr><td><label for="id">DataBase Id</label></td>' +
                '<td><input type="text" id="id" name="id" value="' + item.id + '" readonly></td></tr>' +

                '<tr><td><label for="productName">Product Name</label></td>' +
                '<td><input name="name" type="text" id="productName" value="' + item.name + '" onkeyup="checkProductName()" maxlength="100"required>' +
                '<span id="name-alert" class="alert-info " hidden> Product Already Present</span></td></tr>' +

                '<tr><td><label for="CPUsocket">Socket</label></td>' +
                '<td><input type="text" id="CPUsocket" name="CPUsocket" value="' + item.socket + '" maxlength="10" required></td></tr>' +

                '<tr><td><label for="integratedGpu">Integrated Gpu</label></td>' +
                '<td><input type="radio" id="integratedGpu" name="integratedGpu" value="true"' + integrated + ' required></td></tr>' +

                '<tr><td><label for="noIntegratedGpu">No Integrated Gpu</label></td>' +
                '<td><input type="radio" id="noIntegratedGpu" name="integratedGpu" value="false"' + noIntegrated + ' required></td></tr>' +

                '<tr><td><label for="consumption">Consumption</label></td>' +
                '<td><input type="number" name="consumption" id="consumption" value="' + item.consumption + '" required></td></tr>' +

                '<tr><td><label for="price">Price</label></td>' +
                '<td><input type="number" step="0.01" name="price" value="' + item.price + '" required></td></tr>' +

                '<tr><td><label for="inStock">In Stock</label></td>' +
                '<td><input type="number" id="inStock" name="stock" value="' + item.stock + '" required></td></tr>' +

                '<tr><td></td><td><img src="' + item.imagePath + '" alt=""></td></tr>';
            break;

        case"Memories":
            image=true;
            let ram = (!item.mType) ? " checked " : '';
            let massStorage = (item.mType) ? " checked " : '';
            formHTML =
                ' <input type="hidden" id="requestedItemUpdate" name="requestedItem" value="memories">' +

                '<tr><td><label for="id">DataBase Id</label></td>' +
                '<td><input type="text" id="id" name="id" value="' + item.id + '" readonly></td></tr>' +

                '<tr><td><label for="productName">Product Name</label></td>' +
                '<td><input name="name" type="text" id="productName" value="' + item.name + '" onkeyup="checkProductName()" maxlength="100"required>' +
                '<span id="name-alert" class="alert-info " hidden> Product Already Present</span></td></tr>' +

                ' <tr><td><label for="MEMsocket">Memory Socket</label></td>' +
                '<td><input type="text" id="MEMsocket" name="MEMsocket" value="' + item.socket + '" maxlength="100"required></td></tr>' +

                '<tr><td><label for="Ram">Ram</label></td>' +
                '<td><input type="radio" id="Ram" name="mType" value="false"' + ram + '" required></td></tr>' +

                '<tr><td><label for="MassStorage">MassStorage</label></td>' +
                '<td class="form"><input type="radio" id="MassStorage" name="mType" value="true"' + massStorage + ' required</td></tr>' +

                '<tr><td><label for="amountMemories">Amount Of Memories</label></td>' +
                '<td><input type="number" id="amountMemories" name="amountOfMemories" value=\"' + item.amountMemories + '\" required></td></tr>' +


                '<tr><td><label for="consumption">Consumption</label></td>' +
                '<td><input type="number" name="consumption" id="consumption" value="' + item.consumption + '" required></td></tr>' +

                '<tr><td><label for="price">Price</label></td>' +
                '<td><input type="number" step="0.01" name="price"  value="' + item.price + '"required></td></tr>' +

                '<tr><td><label for="inStock">In Stock</label></td>' +
                '<td><input type="number" id="inStock" name="stock" value="' + item.stock + '"required></td></tr>' +


                '<tr><td></td><td><img src="' + item.imagePath + '" alt=""></td></tr>';            break;
        case"Cases":
            image=true;
            let miniitx = (item.formFactor.toUpperCase( "mini-itx")) ? 'selected' : '';
            let microatx = (item.formFactor.toUpperCase("micro-ATX")) ? 'selected' : '';
            let atx = (item.formFactor.toUpperCase("atx")) ? 'selected' : '';
            let eatx = (item.formFactor.toUpperCase("eatx")) ? 'selected' : '';
            formHTML = ' <input type="hidden" id="requestedItemUpdate" name="requestedItem" value="cases">' +

                '<tr><td><label for="id">DataBase Id</label></td>' +
                '<td><input type="text" id="id" name="id" value="' + item.id + '"   ></td></tr>' +

                '<tr><td><label for="productName">Product Name</label></td>' +
                '<td><input type="text" id="productName" name="name" value="' + item.name + '" onkeyup="checkProductName()" required>' +
                '<span id="name-alert" class="alert-info " hidden> Product Already Present</span></td></tr>' +

                '<tr><td><label for="formFactor">Form Factor</label></td>' +
                '<td><select id="formFActor" name="formFactor"required>' +
                '<option value="mini-itx"' + miniitx + '>Mini-ITX</option>' +
                " <option value='micro-ATX' " + microatx + ">Micro-ATX</option>" +
                "<option value='atx'" + atx + ">ATX</option>" +
                "<option value='eatx'" + eatx + ">EATX</option>" +
                "</select></td></tr>" +


                '<tr><td><label for="price">Price</label></td>' +
                '<td><input type="number" step="0.01" name="price" value="' + item.price + '"required></td></tr>' +

                '<tr><td><label for="inStock">In Stock</label></td>' +
                '<td><input type="number" id="inStock" name="stock" value="' + item.stock + '"required></td></tr>' +


                '<tr><td></td><td><img src="' + item.imagePath + '" alt=""></td></tr>';            break;
        case"MotherBoards":
            image=true;
            let miniitxm = (item.formFactor == "mini-itx") ? 'selected' : '';
            let microatxm = (item.formFactor == "micro-atx") ? 'selected' : '';
            let atxm = (item.formFactor == "atx") ? 'selected' : '';
            let eatxm = (item.formFactor == "eatx") ? 'selected' : '';
            formHTML = ' <input type="hidden" id="requestedItemUpdate" name="requestedItem" value="motherboards">' +

                '<tr><td><label for="id">DataBase Id</label></td>' +
                '<td><input type="text" id="id" name="id" value="' + item.id + '" ></td></tr>' +

                '<tr><td><label for="productName">Product Name</label></td>' +
                '<td><input type="text" id="productName" name="name" value="' + item.name + '" onkeyup="checkProductName()">' +
                '<span id="name-alert" class="alert-info " hidden> Product Already Present</span></td></tr>' +

                "<tr><td><label for='CPUsocket'>CPU Socket</label></td>" +
                " <td><input type='text' id='CPUsocket' name='CPUsocket' value='" + item.cpuSocket + "'required></td></tr>" +

                "<tr><td><label for='RAMsocket'>RAM Socket</label></td>" +
                " <td><input type='text' id='RAMsocket' name='RAMsocket' value='" + item.ramSocket + "'required></td></tr>" +

                " <tr><td><label for='nRAMSockets'>RAM Slots</label></td>" +
                "  <td><input type='number' id='nRAMSockets' name='nRAMSockets' value='" + item.amountSlotRam + "' required></td></tr>" +

                " <tr><td><label for='nSATASockets'>SATA Slots</label></td>" +
                "  <td><input type='number' id='nSATASockets' name='nSATASockets' value='" + item.amountSlotSata + "' required></td></tr>" +

                " <tr><td><label for='nNVMESockets'>NVME Slots</label></td>" +
                "  <td><input type='number' id='nNVMESockets' name='nNVMESockets' value='" + item.amountSlotNvme + "' required></td></tr>" +

                '<tr><td><label for="formFactor">Form Factor</label></td>' +
                '<td><select id="formFActor" name="formFactor"required>' +
                '<option value="mini-itx"' + miniitxm + '>Mini-ITX</option>' +
                " <option value='micro-atx' " + microatxm + ">Micro-ATX</option>" +
                "<option value='atx'" + atxm + ">ATX</option>" +
                "<option value='eatx'" + eatxm + ">EATX</option>" +
                "</select>" +
                "</td></tr>" +

                '<tr><td><label for="consumption">Consumption</label></td>' +
                '<td><input type="number" id="consumption" name="consumption" value="' + item.consumption + '"required></td></tr>' +

                '<tr><td><label for="price">Price</label></td>' +
                '<td><input type="number" step="0.01" name="price" value="' + item.price + '"required></td></tr>' +

                '<tr><td><label for="inStock">In Stock</label></td>' +
                '<td><input type="number" id="inStock" name="stock" value="' + item.stock + '"required></td></tr>' +


                '<tr><td></td><td><img src="' + item.imagePath + '" alt=""></td></tr>';            break
        case"Psus":
            image=true;
            formHTML = ' <input type="hidden" id="requestedItemUpdate" name="requestedItem" value="psus">' +

                '<tr><td><label for="id">DataBase Id</label></td>' +
                '<td><input type="text" id="id" name="id" name="name" value="' + item.id + '" readonly></td></tr>' +

                '<tr><td><label for="productName">Product Name</label></td>' +
                '<td><input type="text" name="name" id="productName" value="' + item.name + '" onkeyup="checkProductName()"required>' +
                '<span id="name-alert" class="alert-info " hidden> Product Already Present</span></td></tr>' +

                '<tr><td><label for="power">Power</label></td>' +
                '<td><input type="text" id="power" name="power" value="' + item.power + '"required></td></tr>' +

                '<tr><td><label for="price">Price</label></td>' +
                '<td><input type="number" step="0.01" name="price" value="' + item.price + '"required></td></tr>' +

                '<tr><td><label for="inStock">In Stock</label></td>' +
                '<td><input type="number" id="inStock" name="stock" value="' + item.stock + '"required></td></tr>' +

                '<tr><td></td><td><img src="' + item.imagePath + '" alt=""></td></tr>';            break;

        case"Users":/*Il modify User è Gestito da Javascript*/
            let admin = (item.admin) ? 'checked' : '';
            formHTML = '';
            break;

    }
    let buttonHTML='<td><input id="saveChanges" type="hidden" name="option" value="update"></td>' +
        '<td><input id="submitDBUpdate" type="submit" class="btn active" onclick="return checkProductName()"></td></tr>';
    console.log(formHTML);
    if(image)
    $("#imageInput").html('<input type="file" id="image" name="image" onchange="checkProductName()">');
    else
        $("#imageInput").html("");
    $("#updateTitle").text("Update Element");
    $("#updateFormBox").html(formHTML);
    $("#buttonSpace").html(buttonHTML)
    toggleOverlayModify();
}

function prepareFormInsert() {
    let image=false;
    let user=false;
    let formHTML;

    switch (selectedElement) {
        case"Gpus":
            image=true;
            formHTML = ' <input type="hidden" id="requestedItemUpdate" name="requestedItem" value="gpus">' +
                '<input type="hidden" name="option" value="insert">' +
                '<input type="hidden" id="id" name="id" value="-1">' +

                '<tr><td><label for="productName">Product Name</label></td>' +
                '<td><input type="text" id="productName" name="name"  onkeyup="checkProductName()">' +
                '<span id="name-alert" class="alert-info " hidden> Product Already Present</span></td></tr>' +

                '<tr><td><label for="consumption">Consumption</label></td>' +
                '<td><input type="number" name="consumption" id="consumption" required></td></tr>' +

                '<tr><td><label for="price">Price</label></td>' +
                '<td><input type="number" step="0.01" name="price" required></td></tr>' +

                '<tr><td><label for="inStock">In Stock</label></td>' +
                '<td><input type="number" id="inStock" name="stock" required></td></tr>'


            break;
        case"Cpus":
            image=true;
            formHTML =
                '<input type="hidden" id="requestedItemUpdate" name="requestedItem" value="cpus">' +
                '<input type="hidden" name="option" value="insert">' +
                '<input type="hidden" id="id" name="id" value="-1">' +


                '<tr><td><label for="productName">Product Name</label></td>' +
                '<td><input name="name" type="text" id="productName" onkeyup="checkProductName()" >' +
                '<span id="name-alert" class="alert-info " hidden> Product Already Present</span></td></tr>' +

                '<tr><td><label for="CPUsocket">Socket</label></td>' +
                '<td><input type="text" id="CPUsocket" name="CPUsocket"required></td></tr>' +

                '<tr><td><label for="integratedGpu">Integrated Gpu</label></td>' +
                '<td><input type="radio" id="integratedGpu" name="integratedGpu" value="true" required></td></tr>' +

                '<tr><td><label for="noIntegratedGpu">No Integrated Gpu</label></td>' +
                '<td><input type="radio" id="noIntegratedGpu" name="integratedGpu" value="false"></td></tr>' +

                '<tr><td><label for="consumption">Consumption</label></td>' +
                '<td><input type="number" name="consumption" id="consumption"required></td></tr>' +

                '<tr><td><label for="price">Price</label></td>' +
                '<td><input type="number" step="0.01" name="price" required></td></tr>' +

                '<tr><td><label for="inStock">In Stock</label></td>' +
                '<td><input type="number" id="inStock" name="stock" required></td></tr>'


            break;

        case"Memories":
            image=true;

            formHTML =
                ' <input type="hidden" id="requestedItemUpdate" name="requestedItem" value="memories">' +
                '<input type="hidden" name="option" value="insert">' +
                '<input type="hidden" id="id" name="id" value="-1">' +


                '<tr><td><label for="productName">Product Name</label></td>' +
                '<td><input name="name" type="text" id="productName" onkeyup="checkProductName()" >' +
                '<span id="name-alert" class="alert-info " hidden> Product Already Present</span></td></tr>' +

                ' <tr><td><label for="MEMsocket">Memory Socket</label></td>' +
                '<td><input type="text" id="MEMsocket" name="MEMsocket" required></td></tr>' +

                '<tr><td><label for="Ram">Ram</label></td>' +
                '<td><input type="radio" id="Ram" name="mType" value="false" required></td></tr>' +

                '<tr><td><label for="MassStorage">MassStorage</label></td>' +
                '<td class="form"><input type="radio" id="MassStorage" name="mType" value="true" required></td></tr>' +

                '<tr><td><label for="amountMemories">Amount Of Memories</label></td>' +
                '<td><input type="number" id="amountMemories" name="amountOfMemories" ></td></tr>' +


                '<tr><td><label for="consumption">Consumption</label></td>' +
                '<td><input type="number" name="consumption" id="consumption" required></td></tr>' +

                '<tr><td><label for="price">Price</label></td>' +
                '<td><input type="number" step="0.01" name="price" required></td></tr>' +

                '<tr><td><label for="inStock">In Stock</label></td>' +
                '<td><input type="number" id="inStock" name="stock" required></td></tr>'


            break;
        case"Cases":
            image=true;
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
                "<option value='atx'>ATX</option>" +
                "<option value='eatx'>EATX</option>" +
                "</select></td></tr>" +


                '<tr><td><label for="price">Price</label></td>' +
                '<td><input type="number" step="0.01" name="price" required></td></tr>' +

                '<tr><td><label for="inStock">In Stock</label></td>' +
                '<td><input type="number" id="inStock" name="stock" required ></td></tr>'


            break;
        case"MotherBoards":
            image=true;

            formHTML = ' <input type="hidden" id="requestedItemUpdate" name="requestedItem" value="motherboards">' +
                '<input type="hidden" name="option" value="insert">' +
                '<input type="hidden" id="id" name="id" value="-1">' +

                '<tr><td><label for="productName">Product Name</label></td>' +
                '<td><input type="text" id="productName" name="name" onkeyup="checkProductName()"required>' +
                '<span id="name-alert" class="alert-info " hidden> Product Already Present</span></td></tr>' +

                "<tr><td><label for='CPUsocket'>CPU Socket</label></td>" +
                " <td><input type='text' id='CPUsocket' name='CPUsocket' required></td></tr>" +

                "<tr><td><label for='RAMsocket'>RAM Socket</label></td>" +
                " <td><input type='text' id='RAMsocket' name='RAMsocket' required></td></tr>" +

                " <tr><td><label for='nRAMSockets'>RAM Slots</label></td>" +
                "  <td><input type='number' id='nRAMSockets' name='nRAMSockets' required></td></tr>" +

                " <tr><td><label for='nSATASockets'>SATA Slots</label></td>" +
                "  <td><input type='number' id='nSATASockets' name='nSATASockets' required></td></tr>" +

                " <tr><td><label for='nNVMESockets'>NVME Slots</label></td>" +
                "  <td><input type='number' id='nNVMESockets' name='nNVMESockets' required></td></tr>" +

                '<tr><td><label for="formFactor">Form Factor</label></td>' +
                '<td><select id="formFActor" name="formFactor" required>' +
                '<option value="mini-itx">Mini-ITX</option>' +
                " <option value='micro-atx'>Micro-ATX</option>" +
                "<option value='atx'>ATX</option>" +
                " <option value='eatx'>EATX</option>" +
                "</select></td></tr>" +

                '<tr><td><label for="consumption">Consumption</label></td>' +
                '<td><input type="number" id="consumption" name="consumption"required></td></tr>' +

                '<tr><td><label for="price">Price</label></td>' +
                '<td><input type="number" step="0.01" name="price" required ></td></tr>' +

                '<tr><td><label for="inStock">In Stock</label></td>' +
                '<td><input type="number" id="inStock" name="stock" required></td></tr>'


            break
        case"Psus":
            image=true;
            formHTML = ' <input type="hidden" id="requestedItemUpdate" name="requestedItem" value="psus">' +
                '<input type="hidden" name="option" value="insert">' +
                '<input type="hidden" id="id" name="id" value="-1">' +

                '<tr><td><label for="productName">Product Name</label></td>' +
                '<td><input type="text" name="name" id="productName"  onkeyup="checkProductName()" required>' +
                '<span id="name-alert" class="alert-info " hidden> Product Already Present</span></td></tr>' +

                '<tr><td><label for="power">Power</label></td>' +
                '<td><input type="text" id="power" name="power" required></td></tr>' +

                '<tr><td><label for="price">Price</label></td>' +
                '<td><input type="number" step="0.01" name="price" required></td></tr>' +

                '<tr><td><label for="inStock">In Stock</label></td>' +
                '<td><input type="number" id="inStock" name="stock" required></td></tr>'


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
        case"Countries":
            formHTML= ' <input type="hidden" id="requestedItemUpdate" name="requestedItem" value="countries">' +
                '<input type="hidden" name="option" value="insert">'+
                '<tr><td><label for="countryName">Country Name</label></td>' +
                '<td><input type="text" id="countryName" name="label"required>'+
                '<tr><td><label for="countryId">Country ID</label></td>' +
                '<td><input type="text" id="countryId" name="id" required>';
            break;
        case "Builds":
            window.location.href="/MYOPSite_war_exploded/build.jsp";
            return
    }

    let buttonHTML='<tr><td><input id="submitDBUpdate" type="submit" class="btn btn-success" value="Insert Element"></td></tr>';
    if(image)
        $("#imageInput").html('<input type="file" id="image" name="image" onchange="checkProductName()">');
    else
        $("#imageInput").html("");
    if(user){
        $("#updateForm").attr("action",".\/admin\/modifyUser");
    }else{
        $("#updateForm").attr("action",".\/admin\/modifyDB");
    }

    console.log(formHTML);
    $("#updateTitle").text("Create Element");
    $("#updateFormBox").html(formHTML);
    $("#buttonSpace").html(buttonHTML)
    toggleOverlayModify();
}