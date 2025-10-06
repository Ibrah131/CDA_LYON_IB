import { useEffect, useMemo, useState } from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";

// CRA proxy: mets "proxy": "http://localhost:8080" dans frontend/package.json
axios.defaults.baseURL = process.env.REACT_APP_API_BASE || "/api";

export default function EmployeeList() {
    const nav = useNavigate();
    const [items, setItems] = useState([]);
    const [q, setQ] = useState("");
    const [loading, setLoading] = useState(true);
    const [err, setErr] = useState("");
    const [pageSize, setPageSize] = useState(5);
    const [page, setPage] = useState(0);

    useEffect(() => {
        let alive = true;
        setLoading(true); setErr("");
        axios.get("/employees", q ? { params: { q } } : undefined)
            .then(res => alive && setItems(Array.isArray(res.data) ? res.data : []))
            .catch(e => alive && setErr(e.message))
            .finally(() => alive && setLoading(false));
        return () => { alive = false; };
    }, [q]);

    const filtered = useMemo(() => {
        if (!q) return items;
        const s = q.toLowerCase();
        return items.filter(e =>
            [e.firstName, e.lastName, e.email].filter(Boolean)
                .some(v => String(v).toLowerCase().includes(s))
        );
    }, [items, q]);

    const total = filtered.length;
    const pageCount = Math.max(1, Math.ceil(total / pageSize));
    const p = Math.min(page, pageCount - 1);
    const from = total === 0 ? 0 : p * pageSize + 1;
    const to = Math.min(total, (p + 1) * pageSize);
    const rows = filtered.slice(p * pageSize, (p + 1) * pageSize);

    async function onDelete(row) {
        if (!window.confirm(`Delete ${row.firstName} ${row.lastName} ?`)) return;
        await axios.delete(`/employees/${row.id ?? row._id}`);
        setItems(prev => prev.filter(x => (x.id ?? x._id) !== (row.id ?? row._id)));
    }

    return (
        <div style={{ maxWidth: 1100, margin: "0 auto", padding: 24 }}>
            <h1 style={{ fontWeight: 700, fontSize: 28, marginBottom: 16 }}>Employees</h1>

            <div style={{ display: "flex", gap: 12, alignItems: "center", marginBottom: 16 }}>
                <button
                    onClick={() => nav("/add-employee")}
                    style={{ background: "#1976d2", color: "#fff", border: 0, borderRadius: 6, padding: "10px 14px" }}
                >
                    ADD EMPLOYEE
                </button>
                <input
                    placeholder="Search"
                    value={q}
                    onChange={(e) => { setQ(e.target.value); setPage(0); }}
                    style={{ flex: 1, padding: "12px 14px", borderRadius: 6, border: "1px solid #e5e7eb" }}
                />
            </div>

            <div style={{ background: "#fff", border: "1px solid #e5e7eb", borderRadius: 8, overflowX: "auto" }}>
                <table style={{ width: "100%", borderCollapse: "collapse" }}>
                    <thead style={{ background: "#f8fafc", color: "#334155" }}>
                    <tr>
                        <th style={{ textAlign: "left", padding: "12px 16px" }}>First Name</th>
                        <th style={{ textAlign: "left", padding: "12px 16px" }}>Last Name</th>
                        <th style={{ textAlign: "left", padding: "12px 16px" }}>Email</th>
                        <th style={{ textAlign: "right", padding: "12px 16px" }}>Actions</th>
                    </tr>
                    </thead>
                    <tbody>
                    {loading && <tr><td colSpan="4" style={{ padding: "12px 16px" }}>Loading…</td></tr>}
                    {!loading && err && <tr><td colSpan="4" style={{ padding: "12px 16px", color: "#b91c1c" }}>{err}</td></tr>}
                    {!loading && !err && rows.length === 0 && <tr><td colSpan="4" style={{ padding: "12px 16px" }}>No data</td></tr>}
                    {!loading && !err && rows.map(e => (
                        <tr key={e.id ?? e._id} style={{ borderTop: "1px solid #e5e7eb" }}>
                            <td style={{ padding: "12px 16px" }}>{e.firstName}</td>
                            <td style={{ padding: "12px 16px" }}>{e.lastName}</td>
                            <td style={{ padding: "12px 16px", color: "#64748b" }}>{e.email}</td>
                            <td style={{ padding: "12px 16px", textAlign: "right" }}>
                                <button
                                    onClick={() => nav(`/edit-employee/${e.id ?? e._id}`)}
                                    style={{ background: "#1976d2", color: "#fff", border: 0, borderRadius: 6, padding: "8px 12px", marginRight: 8 }}
                                >
                                    EDIT
                                </button>
                                <button
                                    onClick={() => onDelete(e)}
                                    style={{ background: "#8e24aa", color: "#fff", border: 0, borderRadius: 6, padding: "8px 12px" }}
                                >
                                    DELETE
                                </button>
                            </td>
                        </tr>
                    ))}
                    </tbody>
                </table>

                <div style={{ display: "flex", gap: 12, justifyContent: "flex-end", alignItems: "center", borderTop: "1px solid #e5e7eb", padding: "10px 14px", color: "#374151" }}>
                    <div>
                        Rows per page:{" "}
                        <select value={pageSize} onChange={(e) => { setPageSize(+e.target.value); setPage(0); }}>
                            {[5, 10, 25, 50].map(n => <option key={n} value={n}>{n}</option>)}
                        </select>
                    </div>
                    <div>{from}-{to} of {total}</div>
                    <div>
                        <button disabled={p === 0} onClick={() => setPage(p - 1)} style={{ marginRight: 6 }}>‹</button>
                        <button disabled={p >= pageCount - 1} onClick={() => setPage(p + 1)}>›</button>
                    </div>
                </div>
            </div>
        </div>
    );
}
