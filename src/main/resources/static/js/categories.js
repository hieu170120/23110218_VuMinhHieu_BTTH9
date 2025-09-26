function parseIds(s){ if(!s) return []; return s.split(',').map(x=>Number(x.trim())).filter(n=>!isNaN(n)); }
function showMsg(id, ok, msg){ const el=document.getElementById(id); el.style.color=ok?'#090':'#c00'; el.textContent=typeof msg==='string'?msg:JSON.stringify(msg); }
function v(id){ return document.getElementById(id).value; }
async function gql(q,v){ const r=await fetch(window.GQL,{method:'POST',headers:{'Content-Type':'application/json'},body:JSON.stringify({query:q,variables:v||{}})}); return r.json(); }

async function loadCategories(){
    const {data}=await gql(`query{ categories { id name images } }`);
    const tb=document.querySelector('#tblCategories tbody'); tb.innerHTML='';
    (data?.categories??[]).forEach(c=>{
        const tr=document.createElement('tr');
        tr.innerHTML='<td>'+c.id+'</td><td>'+(c.name||'')+'</td><td>'+(c.images||'')+'</td>';
        tb.appendChild(tr);
    });
}
async function createCategory(){
    const input={ name:v('cC_name'), images:v('cC_images'), productIds:parseIds(v('cC_productIds')), userIds:parseIds(v('cC_userIds')) };
    const m=`mutation($in:CategoryInput!){ createCategory(input:$in){ id name images } }`;
    const r=await gql(m,{in:input});
    if(r.errors) return showMsg('cC_msg',false,r.errors[0].message);
    showMsg('cC_msg',true,r.data.createCategory); loadCategories();
}
async function updateCategory(){
    const id=Number(v('cU_id')); if(!id) return showMsg('cU_msg',false,'ID?');
    const input={ name:v('cU_name'), images:v('cU_images'), productIds:parseIds(v('cU_productIds')), userIds:parseIds(v('cU_userIds')) };
    const m=`mutation($id:ID!,$in:CategoryInput!){ updateCategory(id:$id,input:$in){ id name images } }`;
    const r=await gql(m,{id,in:input});
    if(r.errors) return showMsg('cU_msg',false,r.errors[0].message);
    showMsg('cU_msg',true,r.data.updateCategory); loadCategories();
}
async function deleteCategory(){
    const id=Number(v('cD_id')); if(!id) return showMsg('cD_msg',false,'ID?');
    const m=`mutation($id:ID!){ deleteCategory(id:$id) }`;
    const r=await gql(m,{id});
    if(r.errors) return showMsg('cD_msg',false,r.errors[0].message);
    showMsg('cD_msg',true,'Deleted? '+r.data.deleteCategory); loadCategories();
}