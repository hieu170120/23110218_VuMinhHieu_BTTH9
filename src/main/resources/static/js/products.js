
function parseIds(s){ if(!s) return []; return s.split(',').map(x=>Number(x.trim())).filter(n=>!isNaN(n)); }
function showMsg(id, ok, msg){ const el=document.getElementById(id); el.style.color=ok?'#090':'#c00'; el.textContent=typeof msg==='string'?msg:JSON.stringify(msg); }
function v(id){ return document.getElementById(id).value; }
function n(id){ const x=v(id); return x===''?null:Number(x); }
async function gql(q,v){ const r=await fetch(window.GQL,{method:'POST',headers:{'Content-Type':'application/json'},body:JSON.stringify({query:q,variables:v||{}})}); return r.json(); }

async function loadProducts(){
    const {data}=await gql(`query{ products { id title price } }`);
    const tb=document.querySelector('#tblProducts tbody'); tb.innerHTML='';
    (data?.products??[]).forEach(p=>{
        const tr=document.createElement('tr');
        tr.innerHTML='<td>'+p.id+'</td><td>'+(p.title||'')+'</td><td>'+(p.price||'')+'</td>';
        tb.appendChild(tr);
    });
}
async function createProduct(){
    const input={ title:v('pC_title'), quantity:n('pC_quantity'), desc:v('pC_desc'), price:n('pC_price'), ownerId:n('pC_ownerId'), categoryIds:parseIds(v('pC_catIds')) };
    const m=`mutation($in:ProductInput!){ createProduct(input:$in){ id title price } }`;
    const r=await gql(m,{in:input});
    if(r.errors) return showMsg('pC_msg',false,r.errors[0].message);
    showMsg('pC_msg',true,r.data.createProduct); loadProducts();
}
async function updateProduct(){
    const id=n('pU_id'); if(!id) return showMsg('pU_msg',false,'ID?');
    const input={ title:v('pU_title'), quantity:n('pU_quantity'), desc:v('pU_desc'), price:n('pU_price'), ownerId:n('pU_ownerId'), categoryIds:parseIds(v('pU_catIds')) };
    const m=`mutation($id:ID!,$in:ProductInput!){ updateProduct(id:$id,input:$in){ id title price } }`;
    const r=await gql(m,{id,in:input});
    if(r.errors) return showMsg('pU_msg',false,r.errors[0].message);
    showMsg('pU_msg',true,r.data.updateProduct); loadProducts();
}
async function deleteProduct(){
    const id=n('pD_id'); if(!id) return showMsg('pD_msg',false,'ID?');
    const m=`mutation($id:ID!){ deleteProduct(id:$id) }`;
    const r=await gql(m,{id});
    if(r.errors) return showMsg('pD_msg',false,r.errors[0].message);
    showMsg('pD_msg',true,'Deleted? '+r.data.deleteProduct); loadProducts();
}
