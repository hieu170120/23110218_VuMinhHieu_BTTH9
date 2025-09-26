async function gql(query, variables) {
    const res = await fetch(window.GQL, {
        method: 'POST', headers:{'Content-Type':'application/json'},
        body: JSON.stringify({ query, variables: variables||{} })
    });
    return res.json();
}

async function loadProductsAsc() {
    const q = `query{ productsByPriceAsc { id title price quantity owner{ id } } }`;
    const { data } = await gql(q);
    const tb = document.querySelector('#tblAsc tbody'); tb.innerHTML='';
    (data?.productsByPriceAsc ?? []).forEach(p=>{
        const tr=document.createElement('tr');
        tr.innerHTML =
            '<td>'+p.id+'</td><td>'+(p.title||'')+'</td><td>'+(p.price||'')+
            '</td><td>'+(p.quantity||'')+'</td><td>'+(p.owner? p.owner.id : '')+'</td>';
        tb.appendChild(tr);
    });
}

async function loadByCategory() {
    const id = Number(document.getElementById('catId').value);
    if (!id) { alert('Nhập categoryId hợp lệ'); return; }
    const q = `query($id:ID!){ productsByCategory(categoryId:$id){ id title price } }`;
    const { data } = await gql(q, { id });
    const tb = document.querySelector('#tblCat tbody'); tb.innerHTML='';
    (data?.productsByCategory ?? []).forEach(p=>{
        const tr=document.createElement('tr');
        tr.innerHTML = '<td>'+p.id+'</td><td>'+(p.title||'')+'</td><td>'+(p.price||'')+'</td>';
        tb.appendChild(tr);
    });
}

// Auto load
loadProductsAsc();