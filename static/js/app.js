const API = '';

async function iniciarSesion() {
    const email = document.getElementById('email').value;
    const contrasena = document.getElementById('contrasena').value;

    try {
        const response = await fetch(`${API}/login`, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ email, contrasena })
        });

        if (response.ok) {
            const token = await response.text();
            localStorage.setItem('token', token);
            window.location.href = '/topicos-view';
        } else {
            mostrarError('mensaje-error', 'Email o contraseña incorrectos');
        }
    } catch (e) {
        mostrarError('mensaje-error', 'Error al conectar con el servidor');
    }
}

async function registrarUsuario() {
    const nombre = document.getElementById('nombre').value;
    const email = document.getElementById('email').value;
    const contrasena = document.getElementById('contrasena').value;

    try {
        const response = await fetch(`${API}/usuarios`, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ nombre, email, contrasena })
        });

        if (response.ok) {
            mostrarInfo('mensaje', '¡Cuenta creada! Redirigiendo...');
            setTimeout(() => window.location.href = '/login', 2000);
        } else {
            mostrarError('mensaje', 'Error al registrar usuario');
        }
    } catch (e) {
        mostrarError('mensaje', 'Error al conectar con el servidor');
    }
}

function cerrarSesion() {
    localStorage.removeItem('token');
    window.location.href = '/';
}

// ========== TÓPICOS ==========
async function cargarTopicos() {
    const token = localStorage.getItem('token');

    if (token) {
        document.getElementById('btn-nuevo').style.display = 'inline-block';
        document.getElementById('btn-logout').style.display = 'inline-block';
        document.getElementById('btn-login').style.display = 'none';
    }

    try {
        const headers = token ? { 'Authorization': `Bearer ${token}` } : {};
        const response = await fetch(`${API}/topicos`, { headers });

        if (response.ok) {
            const topicos = await response.json();
            renderizarTopicos(topicos);
        }
    } catch (e) {
        console.error('Error al cargar tópicos', e);
    }
}

function renderizarTopicos(topicos) {
    const lista = document.getElementById('lista-topicos');
    if (topicos.length === 0) {
        lista.innerHTML = '<p>No hay tópicos aún. ¡Sé el primero en publicar!</p>';
        return;
    }
    lista.innerHTML = topicos.map(t => `
        <div class="topico-card">
            <h3>${t.titulo}</h3>
            <p>${t.mensaje}</p>
            <div class="topico-meta">
                <span>👤 ${t.autor}</span>
                <span>📚 ${t.curso}</span>
                <span>🕐 ${new Date(t.fechaCreacion).toLocaleDateString()}</span>
                <span class="status-badge status-${t.status}">${t.status}</span>
            </div>
        </div>
    `).join('');
}

async function crearTopico() {
    const token = localStorage.getItem('token');
    const titulo = document.getElementById('titulo').value;
    const mensaje = document.getElementById('mensaje').value;
    const curso = document.getElementById('curso').value;

    try {
        const response = await fetch(`${API}/topicos`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': `Bearer ${token}`
            },
            body: JSON.stringify({ titulo, mensaje, curso })
        });

        if (response.ok) {
            ocultarFormulario();
            cargarTopicos();
        } else {
            alert('Error al crear tópico');
        }
    } catch (e) {
        alert('Error al conectar con el servidor');
    }
}

function mostrarFormulario() {
    document.getElementById('form-nuevo-topico').style.display = 'block';
}

function ocultarFormulario() {
    document.getElementById('form-nuevo-topico').style.display = 'none';
}

function mostrarError(id, msg) {
    const el = document.getElementById(id);
    el.textContent = msg;
    el.style.display = 'block';
}

function mostrarInfo(id, msg) {
    const el = document.getElementById(id);
    el.textContent = msg;
    el.style.display = 'block';
}

if (document.getElementById('lista-topicos')) {
    cargarTopicos();
}