<%--
- license.jsp
-
- Copyright (C) 2012-2022 Rafael Corchuelo.
-
- In keeping with the traditional purpose of furthering education and research, it is
- the policy of the copyright owner to permit non-commercial use and redistribution of
- this software. It has been tested carefully, but it is not guaranteed for any particular
- purposes.  The copyright owner does not offer any warranties or representations, nor do
- they accept any liabilities with respect to them.
--%>

<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<%-- 
- Include whatever custom JS scripts you need using the following template:
- 
- <script type="text/javascript" src="libraries/[[component]]/[[version]]/js/[[component]].min.js"></script>
-
- where [[component]] must be replaced by the name of the corresponding component and 
- [[version]] by the corresponding version number.
--%>
<script type="text/javascript">
    function createChart(items, id) {
        let barColors = ["#2F4F4F", "#008080", "#3CB371", "#11cf43", "#004d14", "#769146", "#a5ad97", "#b1f046", "#53574c"];
        let object = items.replaceAll('=', '": ').replaceAll('{', '{"').replaceAll(', ', ', "');
        
        let keys = Object.keys(JSON.parse(object));
        let values = Object.values(JSON.parse(object));
        
        if (keys.length > barColors.length) {
            barColors = generateUniqueColors(barColors, keys);
        }

        let data = {
            labels: keys,
            datasets: [
                {
                    backgroundColor: barColors,
                    data: values
                }
            ]
        };
        let options = {
            scales: {
                yAxes: [
                    {
                        ticks: {
                            suggestedMin: 0.0,
                            suggestedMax: 1.0
                        }
                    }
                ]
            },
            legend: {
                display: false
            }
        };

        let canvas, context;
        canvas = document.getElementById(id);
        context = canvas.getContext("2d");

        new Chart(context, {
            type: "bar",
            data: data,
            options: options
        });
    }

    function createHTMLElement(ids, type, values) {
        let h2 = document.createElement("h2");
        let table = document.createElement("table");
        let title = document.createElement("strong");
        let basicLabel = type + ".dashboard.form";
        let titleLabel = basicLabel + ".title";
        table.setAttribute("class", "table table-sm mt-5");
        getCode(title, titleLabel);
        h2.appendChild(title);
        for (let id of ids) {
            let tr = document.createElement("tr");
            let th = document.createElement("th");
            let td = document.createElement("td");
            let strong = document.createElement("strong");
            let code = basicLabel + ".label." + parseCode(id);
            getCode(strong, code);
            strong.style.fontSize = "18px";
            strong.setAttribute("id", "strong-" + code);
            th.setAttribute("scope", "row");
            let result;
            if(values[ids.indexOf(id)].includes("{")){
                result = document.createElement("canvas");
                result.setAttribute("id", id);
            } else{
                result = document.createElement("span");
                result.style.fontSize = "18px";
                result.innerText = getPrettyText(values[ids.indexOf(id)], strong.innerText);
                strong.setAttribute("class", "mt-5");
            }
            th.appendChild(strong); td.appendChild(result); tr.appendChild(th); tr.appendChild(td); table.appendChild(tr);
        }
        document.getElementsByClassName("panel-body")[0].appendChild(h2).appendChild(table);
    }

    function generateUniqueColors(barColors, keys){
        let size = barColors.length;
        for (let i = 0; i < (keys.length - size); i++) {
            let randomColor = Math.floor(Math.random() * 16777215).toString(16);
            while (barColors.includes("#" + randomColor)) {
                randomColor = Math.floor(Math.random() * 16777215).toString(16);
            }
            barColors.push("#" + randomColor);
        }
        return barColors;
    }

    function getCode(element, label){
        element.innerText = document.getElementById(label).innerHTML.trim();
        document.getElementById(label).parentNode.removeChild(document.getElementById(label));
    }

    function parseCode(id) {
        let result = id.trim().replace(/([A-Z])/g, " $1");
        return result.split(' ').join('-').toLowerCase().trim();
    }

    function getPrettyText(value, text){
        let result = value + " " + text.split(" ")[text.split(" ").length-1];
        if(parseInt(value.trim()) === 1 && result[result.length-1] === "s"){
            result = result.substring(0, result.length-1);
        }
        return result;
    }

    function createDashboard(items, methods) {
        let path = window.location.pathname;
        let slices = path.substring(0, path.search("-dashboard")).split("/");
        let type = slices[slices.length - 1].trim();
        methods = methods.replaceAll('[', '').replaceAll(']', '').split(",");
        items = items.replaceAll('},', '}},').replaceAll('[', '').replaceAll(']', '').split("},");
        finalItems = [];
        for(let item of items){
            item = item.replaceAll(', {', ', {{').split(", {");
            for(let i of item){
                finalItems.push(i);
            }
        }
        if (methods.length === finalItems.length) {
            createHTMLElement(methods, type, finalItems);
            for (let i = 0; i < finalItems.length; i++) {
                if(finalItems[i].includes("{")){
                    createChart(finalItems[i], methods[i]);
                }
            }
        } else {
            console.log("Items and methods must have the same size. Please check the unbind() method from the Service and the createDashboard() method from the .jsp");
        }
    }
</script>