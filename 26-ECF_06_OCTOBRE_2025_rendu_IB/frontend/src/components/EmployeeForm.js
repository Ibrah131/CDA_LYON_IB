import { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import axios from "axios";

axios.defaults.baseURL = process.env.REACT_APP_API_BASE || "/api";
const EMPTY = { firstName: "", lastName: "", email: "", age: "", departmentId: "" };

export default function EmployeeForm() {
  const { id } = useParams();
  const edit = Boolean(id);
  const nav = useNavigate();

  const [data, setData] = useState(EMPTY);
  const [deps, setDeps] = useState([]);
  const [loading, setLoading] = useState(edit);
  const [saving, setSaving] = useState(false);
  const [err, setErr] = useState("");

  useEffect(() => {
    let alive = true;

    axios.get("/departments")
      .then(res => alive && setDeps(Array.isArray(res.data) ? res.data : []))
      .catch(() => alive && setDeps([]));

    if (edit) {
      setLoading(true);
      axios.get(`/employees/${id}`)
        .then(res => {
          if (!alive) return;
          const e = res.data || {};
          setData({
            firstName: e.firstName || "",
            lastName: e.lastName || "",
            email: e.email || "",
            age: e.age ?? "",
            departmentId: e.departmentId ?? e.department?.id ?? ""
          });
        })
        .catch(e => alive && setErr(e.message))
        .finally(() => alive && setLoading(false));
    }
    return () => { alive = false; };
  }, [edit, id]);

  const set = (k,v) => setData(d => ({...d,[k]:v}));

  async function onSubmit(ev){
    ev.preventDefault();
    setErr("");

    if(!data.firstName || !data.lastName || !data.email || !data.age || !data.departmentId){
      setErr("Tous les champs * sont requis."); return;
    }
    if(!/\S+@\S+\.\S+/.test(data.email)){ setErr("Email invalide."); return; }
    if(Number(data.age) <= 0){ setErr("Âge invalide."); return; }

    setSaving(true);
    try{
      const payload = {...data, age:Number(data.age)};
      if(edit) await axios.put(`/employees/${id}`, payload);
      else     await axios.post(`/employees`, payload);
      nav("/employees");
    }catch(e){ setErr(e.message); }
    finally{ setSaving(false); }
  }

  return (
    <div style={{ maxWidth: 800, margin: "0 auto", padding: 24 }}>
      <h2 style={{ fontWeight: 700, fontSize: 28, marginBottom: 16 }}>
        {edit ? "Edit Employee" : "Add Employee"}
      </h2>

      {loading ? "Loading…" : (
        <form onSubmit={onSubmit} style={{ background:"#fff", border:"1px solid #e5e7eb", borderRadius:8, padding:20 }} noValidate>
          <div style={{ display:"grid", gap:12 }}>
            <input className="input" placeholder="First Name *"
                   value={data.firstName} onChange={e=>set("firstName",e.target.value)}
                   style={{ padding:"12px 14px", borderRadius:6, border:"1px solid #e5e7eb" }} />
            <input placeholder="Last Name *"
                   value={data.lastName} onChange={e=>set("lastName",e.target.value)}
                   style={{ padding:"12px 14px", borderRadius:6, border:"1px solid #e5e7eb" }} />
            <input type="email" placeholder="Email *"
                   value={data.email} onChange={e=>set("email",e.target.value)}
                   style={{ padding:"12px 14px", borderRadius:6, border:"1px solid #e5e7eb" }} />
            <input type="number" placeholder="Age *"
                   value={data.age} onChange={e=>set("age",e.target.value)}
                   style={{ padding:"12px 14px", borderRadius:6, border:"1px solid #e5e7eb" }} />
            <select value={data.departmentId} onChange={e=>set("departmentId",e.target.value)}
                    style={{ padding:"12px 14px", borderRadius:6, border:"1px solid #e5e7eb" }}>
              <option value="">Department *</option>
              {deps.map(d => (
                <option key={d.id ?? d._id} value={d.id ?? d._id}>
                  {d.name || d.title || `Department ${d.id ?? d._id}`}
                </option>
              ))}
            </select>
          </div>

          {err && <p style={{ color:"#b91c1c", marginTop:12 }}>{err}</p>}

          <div style={{ marginTop:18 }}>
            <button disabled={saving} style={{ background:"#1976d2", color:"#fff", border:0, borderRadius:6, padding:"10px 14px" }}>
              {saving ? "Saving…" : "SAVE"}
            </button>
          </div>
        </form>
      )}
    </div>
  );
}
