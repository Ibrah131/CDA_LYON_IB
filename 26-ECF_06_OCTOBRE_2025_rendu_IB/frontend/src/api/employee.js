const API_BASE = process.env.REACT_APP_API_BASE || '/api'

async function asJson(res){
  if(!res.ok){
    const t = await res.text().catch(()=> '')
    throw new Error(`HTTP ${res.status} ${res.statusText} ${t}`)
  }
  return res.status === 204 ? null : res.json()
}

export const EmployeesApi = {
  list: (q) => fetch(`${API_BASE}/employees${q?`?q=${encodeURIComponent(q)}`:''}`).then(asJson),
  get:  (id) => fetch(`${API_BASE}/employees/${id}`).then(asJson),
  create: (data) => fetch(`${API_BASE}/employees`, {
    method:'POST', headers:{'Content-Type':'application/json'}, body: JSON.stringify(data)
  }).then(asJson),
  update: (id, data) => fetch(`${API_BASE}/employees/${id}`, {
    method:'PUT', headers:{'Content-Type':'application/json'}, body: JSON.stringify(data)
  }).then(asJson),
  remove: (id) => fetch(`${API_BASE}/employees/${id}`, { method:'DELETE' }).then(asJson),
}

export const DepartmentsApi = {
  list: () => fetch(`${API_BASE}/departments`).then(asJson)
}
