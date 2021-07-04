<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<%--
  Created by IntelliJ IDEA.
  User: Esterno
  Date: 01/07/2021
  Time: 21:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>MYOP-${option=='insert'}</title>
    <meta name="viewport" content="width=device-witdth, initial-scale=1.0"/>
    <link rel="stylesheet" href="../bootstrap/css/bootstrap.css"/>
    <link rel="stylesheet" href="../customcss/general.css">
</head>
<body>

<script src="../bootstrap/js/bootstrap.js" defer></script>
<script src="../bootstrap/popper.js" defer></script>
<jsp:include page="/WEB-INF/pagecomponents/header.jsp"></jsp:include>
<script src="../jslibraries/jQuery.js" defer></script>
<script src="../jslibraries/utilities.js" defer></script>
<body>
<form enctype="multipart/form-data">
    <table>
        <c:choose>
            <c:when test="${requestedItem=='gpus'}">
                <c:if test="${option!='insert'}">
                    <tr>
                        <td>
                            <label for="id">DataBase Id</label>
                        </td>
                        <td>
                            <input type="hidden" name="id" value="${item.id}" disabled>
                        </td>
                    </tr>
                </c:if>
                <tr>
                    <td>
                        <label for="productName">Product Name</label>
                    </td>
                    <td>
                        <input type="text" id="productName" value="${item.name}">
                    </td>
                </tr>

                <tr>
                    <td><input type="number" id="power" value="${item.consumption}"></td>
                </tr>
                <tr>
                    <td><input type="number" id="stock" value="${item.stock}"></td>
                </tr>
                <tr>
                    <td>
                        <label for="price">Price</label>
                    </td>
                    <td>
                        <input type="number" step="0.01" value="${item.price}">
                    </td>
                </tr>
                <tr>
                    <td><input type="file" id="image" name="image"></td>
                </tr>
                <tr>
                    <td><img src="${item.imagePath}"></td>
                </tr>

            </c:when>
            <c:when test="${requestedItem=='cpus'}">
                <c:if test="${option!='insert'}">
                    <tr>
                        <td>
                            <label for="id">DataBase Id</label>
                        </td>
                        <td>
                            <input type="hidden" name="id" value="${item.id}" disabled>
                        </td>
                    </tr>
                </c:if>
                <input type='hidden' id='requestedItem' name='requestedItem' value='cpus'>

                <tr>
                    <td>
                        <label for="productName">Product Name</label>
                    </td>
                    <td>
                        <input type="text" id="productName" value="${item.name}">
                    </td>
                </tr>



                <tr>
                    <td><label for='CPUsocket'>Socket</label></td>
                    <td>
                        <input type='text' id='CPUsocket' name='CPUsocket' value="${item.socket}">
                    </td>
                </tr>


                <tr>
                    <td><label for='integratedGpu'>Integrated Gpu</label></td>
                    <td>
                        <input type='radio' id='integratedGpu' name='integratedGpu' value='true'
                               <c:if test="${item.integratedGpu==true}">checked</c:if>>
                    </td>
                </tr>
                <tr>
                    <td><label for='noIntegratedGpu'>No Integrated Gpu</label></td>
                    <td>
                        <input type='radio' id='noIntegratedGpu' name='integratedGpu' value='false'
                               <c:if test="${item.integratedGpu==false}">checked</c:if>>
                    </td>
                </tr>

                <tr>
                    <td>
                        <label for="consumption">Consumption</label>
                    </td>
                    <td>
                        <input type="number" id="consumption" value="${item.consumption}">
                    </td>
                </tr>
                <tr>
                    <td>
                        <label for="price">Price</label>
                    </td>
                    <td>
                        <input type="number" step="0.01" value="${item.price}">
                    </td>
                </tr>
                <input type="file" id="image" name="image">
                <img src="${item.imagePath}">

            </c:when>

            <c:when test="${requestedItem=='memories'}">
                <c:if test="${option!='insert'}"><input type="hidden" name="id" value="${item.id}"></c:if>

                <input type='hidden' id='requestedItem' name='requestedItem' value='memories'>
                <tr>
                    <td>
                        <label for="productName">Product Name</label>
                    </td>
                    <td>
                        <input type="text" id="productName" value="${item.name}">
                    </td>
                </tr>


                <tr>
                    <td><label for='MEMsocket'>Memory Socket</label></td>
                    <td><input type='text' id='MEMsocket' name='MEMsocket' value="${item.socket}"></td>
                </tr>

                <tr>
                    <td><label for='Ram'>Ram</label></td>
                    <td><input type='radio' id='Ram' name='mType' value='false'
                               <c:if test="${item.mType==false}">checked</c:if>></td>
                </tr>

                <tr>
                    <td><label for='MassStorage'>MassStorage</label></td>
                    <td class='form'><input type='radio' id='MassStorage' name='mType' value='true'
                                            <c:if test="${item.mType==true}">checked</c:if>></td>
                </tr>

                <tr>
                    <td><label for='amountMemories'>Amount Of Memories</label></td>
                    <td><input type='number' id='amountMemories' name='amountOfMemories'></td>
                </tr>

                <tr>
                    <td>
                        <label for="consumption">Consumption</label>
                    </td>
                    <td>
                        <input type="number" id="consumption" value="${item.consumption}">
                    </td>
                </tr>
                <tr>
                    <td>
                        <label for="price">Price</label>
                    </td>
                    <td>
                        <input type="number" step="0.01" value="${item.price}">
                    </td>
                </tr>
                <tr>
                    <td>
                        <label for="inStock">In Stock</label>
                    </td>
                    <td>
                        <input type="number" id="inStock" value="${item.stock}">
                    </td>
                </tr>
                <input type="file" id="image" name="image">
                <img src="${item.imagePath}">

            </c:when>
            <c:when test="${requestedItem=='cases'}">
                <c:if test="${option!='insert'}">
                    <tr>
                        <td>
                            <label for="id">DataBase Id</label>
                        </td>
                        <td>
                            <input type="hidden" name="id" value="${item.id}" disabled>
                        </td>
                    </tr>
                </c:if>
                <input type='hidden' id='requestedItem' name='requestedItem' value='memories'>
                <tr>
                    <td>
                        <label for="productName">Product Name</label>
                    </td>
                    <td>
                        <input type="text" id="productName" value="${item.name}">
                    </td>
                </tr>

                <tr>
                <tr>
                    <td><label for='formFactor'>Form Factor</label></td>
                    <td><select id='formFActor' name='formFactor'>
                        <option value='mini-itx' <c:if test="${item.formFactor=='mini-itx'}">selected</c:if>>Mini-ITX
                        </option>
                        <option value='micro-atx' <c:if test="${item.formFactor=='micro-itx'}">selected</c:if>>
                            Micro-ATX
                        </option>
                        <option value='atx' <c:if test="${item.formFactor=='atx'}">selected</c:if>>ATX</option>
                    </select></td>
                </tr>
                <tr>
                    <td>
                        <label for="price">Price</label>
                    </td>
                    <td>
                        <input type="number" step="0.01" value="${item.price}">
                    </td>
                </tr>
                <tr>
                    <td>
                        <label for="inStock">In Stock</label>
                    </td>
                    <td>
                        <input type="number" id="inStock" value="${item.stock}">
                    </td>
                </tr>
                <input type="file" id="image" name="image">
                <img src="${item.imagePath}">

            </c:when>

            <c:when test="${requestedItem=='psus'}">
                <c:if test="${option!='insert'}">
                    <tr>
                        <td>
                            <label for="id">DataBase Id</label>
                        </td>
                        <td>
                            <input type="hidden" name="id" value="${item.id}" disabled>
                        </td>
                    </tr>
                </c:if>
                <input type='hidden' id='requestedItem' name='requestedItem' value='psus'>
                <tr>
                    <td>
                        <label for="productName">Product Name</label>
                    </td>
                    <td>
                        <input type="text" id="productName" value="${item.name}">
                    </td>
                </tr>
                <tr>
                    <td>
                        <label for="consumption">Power</label>
                    </td>
                    <td>
                        <input type="number" id="power" value="${item.power}">
                    </td>
                </tr>
                <tr>
                    <td>
                        <label for="price">Price</label>
                    </td>
                    <td>
                        <input type="number" step="0.01" value="${item.price}">
                    </td>
                </tr>
                <tr>
                    <td>
                        <label for="inStock">In Stock</label>
                    </td>
                    <td>
                        <input type="number" id="inStock" value="${item.stock}">
                    </td>
                </tr>
                <input type="file" id="image" name="image">
                <img src="${item.imagePath}">

            </c:when>
            <c:when test="${requestedItem=='users'}">
                <input type='hidden' id='requestedItem' name='requestedItem' value='users'>
                    <tr>
                        <td>
                            <label for="email">User Email</label>
                        </td>
                        <td>
                            <input type="hidden" id="email" name="email" value="${item.email}" <c:if test="${option!='insert'}"> disabled </c:if>>
                        </td>
                    </tr>


                <tr>
                    <td>
                        <label for="firstName">Firstname</label>
                    </td>
                    <td>
                        <input type="text" id="firstName" value="${item.firstName}">
                    </td>
                </tr>

                <tr>
                    <td>
                        <label for="lastName">Lastname</label>
                    </td>
                    <td>
                        <input type="text" id="lastName" value="${item.lastName}">
                    </td>
                </tr>

                <tr>
                    <td><label for='admin'>Admin</label></td>
                    <td><input type='radio' id='admin' name='admin' value='true'
                               <c:if test="${item.admin==false}">checked</c:if>></td>
                </tr>

                <tr>
                    <td><label for='normalUser'>Normal User</label></td>
                   <td> <input type='radio' id='normalUser' name='admin' value='false'
                                            <c:if test="${item.admin==true}">checked</c:if>>
                   </td>
                </tr>
            </c:when>
            <c:when test="${requestedItem=='motherboards'}">
                <c:if test="${option!='insert'}"><input type="hidden" name="id" value="${item.id}"></c:if>

                <input type='hidden' id='requestedItem' name='requestedItem' value='motherboards'>
                <tr>
                    <td>
                        <label for="productName">Product Name</label>
                    </td>
                    <td>
                        <input type="text" id="productName" value="${item.name}">
                    </td>
                </tr>


                <tr><td><label for='CPUsocket'>CPU Socket</label></td>
                <td><input type='text' id='CPUsocket' name='CPUsocket' value="${item.cpuSocket}"></td></tr>

                <tr><td><label for='RAMsocket'>RAM Type</label></td>
                <td><input type='text' id='RAMsocket' name='RAMsocket' value="${item.ramSocket}" ></td></tr>

                <tr><td><label for='nRAMSockets'>RAM Slots</label></td>
                <td><input type='number' id='nRAMSockets' name='nRAMSockets' value="${item.amountSlotRam}" ></td></tr>

                <tr><td><label for='nSATASockets'>SATA Sockets</label></td>
                <td><input type='number' id='nSATASockets' name='nSATASockets' value="${item.amountSlotSata}"></td></tr>

                <tr><td><label for='nNVMESockets'>NVME Sockets</label></td>
                <td><input type='number' id='nNVMESockets' name='nNVMESockets' value="${item.amountSlotNvme}"></td></tr>

                <tr>
                    <td><label for='formFactor'>Form Factor</label></td>
                    <td><select id='formFActor' name='formFactor'>
                        <option value='mini-itx' <c:if test="${item.formFactor=='mini-itx'}">selected</c:if>>Mini-ITX
                        </option>
                        <option value='micro-atx' <c:if test="${item.formFactor=='micro-itx'}">selected</c:if>>
                            Micro-ATX
                        </option>
                        <option value='atx' <c:if test="${item.formFactor=='atx'}">selected</c:if>>ATX</option>
                    </select></td>
                </tr>

                <tr>
                    <td>
                        <label for="consumption">Consumption</label>
                    </td>
                    <td>
                        <input type="number" id="consumption" value="${item.consumption}">
                    </td>
                </tr>
                <tr>
                    <td>
                        <label for="price">Price</label>
                    </td>
                    <td>
                        <input type="number" step="0.01" value="${item.price}">
                    </td>
                </tr>
                <tr>
                    <td>
                        <label for="inStock">In Stock</label>
                    </td>
                    <td>
                        <input type="number" id="inStock" value="${item.stock}">
                    </td>
                </tr>
                <input type="file" id="image" name="image">
                <img src="${item.imagePath}">

            </c:when>
        </c:choose>
        <tr>
            <td>
                <c:if test="${option!='insert'}"><button type="submit" name="option" value="delete">Delete</button></c:if>
            </td>
            <td>
                <c:if test="${option!='insert'}"><button type="submit" name="option" value="update">Save Changes</button></c:if>
            </td>
            <td>
                <c:if test="${option=='insert'}"><button type="submit" name="option" value="create">Create Element</button></c:if>
            </td>
        </tr>
    </table>
</form>
</body>
</html>
