/* ==========================================
   CONFIGURAZIONE
========================================== */
const API_BASE = "http://localhost:8081";

/* ==========================================
   AUTH STATE
========================================== */
let auth = {
    token: localStorage.getItem("token") || null,
    email: localStorage.getItem("email") || null,
    user: null
};

/* ==========================================
   FUNZIONE GENERALE PER CHIAMATE API
========================================== */
async function api(path, options = {}) {
    const headers = options.headers || {};

    if (auth.token) {
        headers["Authorization"] = "Bearer " + auth.token;
    }
    if (options.body) {
        headers["Content-Type"] = "application/json";
    }

    const res = await fetch(API_BASE + path, {
        ...options,
        headers
    });

    if (!res.ok) {
        const text = await res.text();
        throw new Error(text || "Errore API");
    }

    if (res.status === 204) return null;
    return res.json();
}

/* ==========================================
   ON LOAD
========================================== */
document.addEventListener("DOMContentLoaded", async () => {
    setupAuthTabs();
    setupSidebarNavigation();
    setupAdminForm();

    if (auth.token && auth.email) {
        try {
            await loadUser();
            goToDashboard();
        } catch (err) {
            logout();
        }
    }
});

/* ==========================================
   LOGIN / REGISTER
========================================== */
function setupAuthTabs() {
    const tabLogin = document.getElementById("tab-login");
    const tabRegister = document.getElementById("tab-register");
    const loginForm = document.getElementById("login-form");
    const registerForm = document.getElementById("register-form");

    tabLogin.onclick = () => {
        tabLogin.classList.add("auth-tab--active");
        tabRegister.classList.remove("auth-tab--active");

        loginForm.classList.add("form--active");
        registerForm.classList.remove("form--active");
    };

    tabRegister.onclick = () => {
        tabRegister.classList.add("auth-tab--active");
        tabLogin.classList.remove("auth-tab--active");

        registerForm.classList.add("form--active");
        loginForm.classList.remove("form--active");
    };

    loginForm.onsubmit = async (e) => {
        e.preventDefault();

        const email = document.getElementById("login-email").value;
        const password = document.getElementById("login-password").value;

        try {
            const data = await api("/api/auth/login", {
                method: "POST",
                body: JSON.stringify({ email, password })
            });

            auth.token = data.token;
            auth.email = email;

            localStorage.setItem("token", data.token);
            localStorage.setItem("email", email);

            await loadUser();
            goToDashboard();
        } catch {
            alert("Email o password errati.");
        }
    };

    registerForm.onsubmit = async (e) => {
        e.preventDefault();

        const payload = {
            fName: document.getElementById("register-fname").value,
            lName: document.getElementById("register-lname").value,
            email: document.getElementById("register-email").value,
            password: document.getElementById("register-password").value
        };

        try {
            const data = await api("/api/auth/register", {
                method: "POST",
                body: JSON.stringify(payload)
            });

            auth.token = data.token;
            auth.email = payload.email;

            localStorage.setItem("token", data.token);
            localStorage.setItem("email", payload.email);

            await loadUser();
            goToDashboard();
        } catch {
            alert("Registrazione fallita.");
        }
    };
}

/* ==========================================
   CARICA UTENTE
========================================== */
async function loadUser() {
    auth.user = await api(`/api/users/email/${auth.email}`);

    document.getElementById("topbar-user-info").innerText =
        `${auth.user.fname} ${auth.user.lname} (${auth.user.role})`;

    // Ruolo admin: supporta sia "ADMIN" che "ROLE_ADMIN"
    const isAdmin = auth.user.role.includes("ADMIN");

    document.querySelectorAll(".admin-only").forEach(el => {
        el.style.display = isAdmin ? "block" : "none";
    });
}

/* ==========================================
   DASHBOARD
========================================== */
function goToDashboard() {
    document.getElementById("view-auth").classList.remove("view--active");
    document.getElementById("view-dashboard").classList.add("view--active");
    document.getElementById("topbar").classList.remove("hidden");

    document.getElementById("welcome-title").innerText =
        `Ciao ${auth.user.fname}!`;

    loadUserCars();
}

/* LOGOUT */
document.getElementById("btn-logout").onclick = logout;

function logout() {
    localStorage.clear();
    location.reload();
}

/* ==========================================
   SIDEBAR NAVIGATION
========================================== */
function setupSidebarNavigation() {
    document.querySelectorAll(".sidebar__link").forEach(btn => {
        btn.onclick = () => {
            document.querySelectorAll(".sidebar__link")
                .forEach(el => el.classList.remove("sidebar__link--active"));

            btn.classList.add("sidebar__link--active");

            document.querySelectorAll(".dashboard-section")
                .forEach(sec => sec.classList.remove("dashboard-section--active"));

            const target = btn.dataset.target;
            document.getElementById(target).classList.add("dashboard-section--active");

            if (target === "dashboard-cars") loadUserCars();
            if (target === "admin-panel") loadAdminCars();
        };
    });
}

/* ==========================================
   USER – LISTA AUTO
========================================== */
async function loadUserCars() {
    const cars = await api("/api/cars");
    const grid = document.getElementById("cars-grid");
    grid.innerHTML = "";

    cars.forEach(car => {
        const card = document.createElement("div");
        card.className = "car-card";

        card.innerHTML = `
            <img src="${car.imageUrl || 'https://via.placeholder.com/300'}">
            <h3>${car.brand} ${car.model}</h3>
            <p>${car.price} €</p>
            <span class="badge">${car.year}</span>
            <span class="badge">${car.hp} hp</span>
        `;

        grid.appendChild(card);
    });
}

/* ==========================================
   ADMIN – CRUD AUTO
========================================== */

/* Carica tabella admin */
async function loadAdminCars() {
    const cars = await api("/api/cars");
    const table = document.getElementById("admin-cars-table");
    table.innerHTML = "";

    cars.forEach(car => {
        const tr = document.createElement("tr");
        tr.innerHTML = `
            <td>${car.id}</td>
            <td>${car.brand}</td>
            <td>${car.model}</td>
            <td>${car.year}</td>
            <td>${car.price}</td>
            <td>
                <button class="btn btn-sm" onclick="editCar(${car.id})">Modifica</button>
                <button class="btn btn-sm" onclick="deleteCar(${car.id})">Elimina</button>
            </td>
        `;
        table.appendChild(tr);
    });
}

/* Elimina auto */
async function deleteCar(id) {
    if (!confirm("Vuoi eliminare questa auto?")) return;

    await api(`/api/admins/cars/${id}`, { method: "DELETE" });
    loadAdminCars();
}

/* Carica form per modifica */
async function editCar(id) {
    const cars = await api("/api/cars");
    const car = cars.find(c => c.id === id);

    document.getElementById("admin-car-id").value = car.id;
    document.getElementById("admin-car-brand").value = car.brand;
    document.getElementById("admin-car-model").value = car.model;
    document.getElementById("admin-car-year").value = car.year;
    document.getElementById("admin-car-color").value = car.color;
    document.getElementById("admin-car-price").value = car.price;
    document.getElementById("admin-car-cc").value = car.cc;
    document.getElementById("admin-car-hp").value = car.hp;
    document.getElementById("admin-car-km").value = car.km;
    document.getElementById("admin-car-image").value = car.imageUrl || "";

    document.getElementById("admin-form-title").innerText = "Modifica auto";
    document.getElementById("admin-cancel-edit").classList.remove("hidden");
}

/* Reset form */
function resetAdminForm() {
    document.getElementById("admin-car-id").value = "";
    document.getElementById("admin-car-form").reset();
    document.getElementById("admin-form-title").innerText = "Aggiungi nuova auto";
    document.getElementById("admin-cancel-edit").classList.add("hidden");
}

/* Submit form */
function setupAdminForm() {
    document.getElementById("admin-car-form").onsubmit = async (e) => {
        e.preventDefault();

        const id = document.getElementById("admin-car-id").value;

        const payload = {
            brand: document.getElementById("admin-car-brand").value,
            model: document.getElementById("admin-car-model").value,
            year: Number(document.getElementById("admin-car-year").value),
            color: document.getElementById("admin-car-color").value,
            price: Number(document.getElementById("admin-car-price").value),
            cc: Number(document.getElementById("admin-car-cc").value),
            hp: Number(document.getElementById("admin-car-hp").value),
            km: Number(document.getElementById("admin-car-km").value),
            imageUrl: document.getElementById("admin-car-image").value
        };

        if (id) {
            await api(`/api/admins/cars/${id}`, {
                method: "PUT",
                body: JSON.stringify(payload)
            });
        } else {
            await api("/api/admins/cars", {
                method: "POST",
                body: JSON.stringify(payload)
            });
        }

        resetAdminForm();
        loadAdminCars();
    };

    document.getElementById("admin-cancel-edit").onclick = resetAdminForm;
}
