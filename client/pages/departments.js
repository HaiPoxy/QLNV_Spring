addListeners();
findAll();

async function addListeners() {
    document.onclick = hideContextMenu;
    document.getElementById("filter-form").onsubmit = onFilterFormSubmit;
    document.getElementById("refresh").onclick = findAll;
    document.getElementById("add").onclick = onAddClick;
    document.getElementById("create-form").onsubmit = onCreateFormSubmit;
    document.getElementById("delete-all").onclick = onDeleteAllClick;
    document.getElementById("edit").onclick = onEditClick;
    document.getElementById("update-form").onsubmit = onUpdateFormSubmit;
    document.getElementById("delete").onclick = onDeleteClick;
    document.getElementById("thead").onclick = onTheadClick;
    document.getElementById("tbody").onclick = onTbodyClick;
    document.getElementById("tbody").oncontextmenu = onTbodyContextMenu;
    document.getElementById("size").onchange = onSizeChange;
}

async function onFilterFormSubmit(e) {
    e.preventDefault();
    findAll();
}

async function onAddClick() {
    document.getElementById("create-form").reset();
    document.getElementById("create-dialog").showModal();
}

async function onCreateFormSubmit() {
    await fetch("http://localhost:8080/api/v1/departments", {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify({
            name: document.getElementById("c-name").value,
            totalMembers: document.getElementById("c-total-members").value,
            type: document.getElementById("c-type").value,
        }),
    });
    findAll();
}

async function onEditClick() {
    hideContextMenu();
    document.getElementById("update-form").reset();
    const selectedCells = document.querySelectorAll("#tbody > .selected > td");
    const cells = Array.from(selectedCells).map((selectedCell) => selectedCell.innerText);
    document.getElementById("u-id").value = cells[0];
    document.getElementById("u-name").value = cells[1];
    document.getElementById("u-total-members").value = cells[2];
    document.getElementById("u-type").value = cells[3];
    document.getElementById("update-dialog").showModal();
}

async function onUpdateFormSubmit() {
    const id = document.getElementById("u-id").value;
    await fetch("http://localhost:8080/api/v1/departments/" + id, {
        method: "PUT",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify({
            name: document.getElementById("u-name").value,
            totalMembers: document.getElementById("u-total-members").value,
            type: document.getElementById("u-type").value,
        }),
    });
    findAll();
}

async function onDeleteClick() {
    hideContextMenu();
    const selectedId = document.querySelector("#tbody > .selected > td:first-child");
    const id = selectedId.innerText;
    await fetch("http://localhost:8080/api/v1/departments/" + id, { method: "DELETE" });
    findAll();
}

async function onDeleteAllClick() {
    const selectedIds = document.querySelectorAll("#tbody > .selected > td:first-child");
    const ids = Array.from(selectedIds).map((selectedId) => selectedId.innerText);
    await fetch("http://localhost:8080/api/v1/departments", {
        method: "DELETE",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify(ids),
    });
    findAll();
}

async function onTheadClick(e) {
    for (const i of this.getElementsByTagName("i")) {
        const classes = i.classList;
        classes.remove("fa-sort-up", "fa-sort-down");
        classes.add("fa-sort");
    }
    const th = e.target.closest("th");
    const newSort = th.dataset.sort;
    const classes = th.children[0].classList;
    const [sort, direction] = this.dataset.sort.split(",");
    classes.remove("fa-sort");
    if (sort == newSort && direction == "asc") {
        classes.add("fa-sort-down");
        this.dataset.sort = newSort + ",desc";
    } else {
        classes.add("fa-sort-up");
        this.dataset.sort = newSort + ",asc";
    }
    findAll();
}

async function onTbodyClick(e) {
    const tr = e.target.closest("tr");
    if (e.ctrlKey) {
        tr.classList.toggle("selected");
    } else {
        for (const row of this.getElementsByTagName("tr")) {
            row.classList.remove("selected");
        }
        tr.classList.add("selected");
    }
    const length = this.getElementsByClassName("selected").length;
    const deleteAll = document.getElementById("delete-all");
    if (length == 0) {
        deleteAll.setAttribute("disabled", "");
    } else {
        deleteAll.removeAttribute("disabled");
    }
}

async function onTbodyContextMenu(e) {
    e.preventDefault();
    for (const row of this.getElementsByTagName("tr")) {
        row.classList.remove("selected");
    }
    const tr = e.target.closest("tr");
    tr.classList.add("selected");
    showContextMenu(e.pageX, e.pageY);
}

async function onSizeChange() {
    document.getElementById("page").value = 1;
    findAll();
}

async function findAll() {
    showLoading();
    const params = {
        page: document.getElementById("page").value,
        size: document.getElementById("size").value,
        sort: document.getElementById("thead").dataset.sort,
        search: document.getElementById("search").value,
        type: document.getElementById("type").value,
        minTotalMembers: document.getElementById("min-total-members").value,
        maxTotalMembers: document.getElementById("max-total-members").value,
        minCreatedDate: document.getElementById("min-created-date").value,
        maxCreatedDate: document.getElementById("max-created-date").value,
    };
    const url = new URL("http://localhost:8080/api/v1/departments");
    url.search = new URLSearchParams(params).toString();
    const response = await fetch(url);
    const body = await response.json();
    showDepartments(body.content);
    updatePagination(body);
    setTimeout(() => hideLoading(), Math.random() * 250);
}

async function showDepartments(departments) {
    document.getElementById("tbody").innerHTML = departments
        .map((department) =>
            `<tr>
				<td>${department.id}</td>
				<td>${department.name}</td>
				<td>${department.totalMembers}</td>
				<td>${department.type}</td>
				<td>${new Date(department.createdAt).toLocaleString()}</td>
				<td>${new Date(department.updatedAt).toLocaleString()}</td>
			</tr>`.trim()
        )
        .join("");
}

async function updatePagination({ first, last, pageable, totalPages }) {
    const firstPage = document.getElementById("first-page");
    const prevPage = document.getElementById("prev-page");
    const page = document.getElementById("page");
    const size = document.getElementById("size");
    const nextPage = document.getElementById("next-page");
    const lastPage = document.getElementById("last-page");
    const currentPage = pageable.pageNumber + 1;
    page.value = currentPage;
    size.value = pageable.pageSize;
    if (first) {
        firstPage.setAttribute("disabled", "");
        prevPage.setAttribute("disabled", "");
    } else {
        firstPage.removeAttribute("disabled");
        prevPage.removeAttribute("disabled");
    }
    if (last) {
        nextPage.setAttribute("disabled", "");
        lastPage.setAttribute("disabled", "");
    } else {
        nextPage.removeAttribute("disabled");
        lastPage.removeAttribute("disabled");
    }
    page.onkeydown = function (e) {
        if (e.key != "Enter") {
            return;
        }
        if (this.value < 1 || this.value > totalPages) {
            this.value = currentPage;
        }
        findAll();
    };
    firstPage.onclick = function () {
        page.value = 1;
        findAll();
    };
    prevPage.onclick = function () {
        page.value = currentPage - 1;
        findAll();
    };
    nextPage.onclick = function () {
        page.value = currentPage + 1;
        findAll();
    };
    lastPage.onclick = function () {
        page.value = totalPages;
        findAll();
    };
}

function showContextMenu(x, y) {
    const menu = document.getElementById("context-menu");
    menu.style.display = "flex";
    menu.style.left = x + "px";
    menu.style.top = y + "px";
}

function hideContextMenu() {
    document.getElementById("context-menu").style.display = "none";
}

function showLoading() {
    document.getElementById("loading").style.display = "flex";
}

function hideLoading() {
    document.getElementById("loading").style.display = "none";
}
