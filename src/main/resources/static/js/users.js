function parseIds(s){ if(!s) return []; return s.split(',').map(x=>Number(x.trim())).filter(n=>!isNaN(n)); }
function showMsg(id, ok, msg){ const el=document.getElementById(id); el.style.color=ok?'#090':'#c00'; el.textContent=typeof msg==='string'?msg:JSON.stringify(msg); }

async function gql(q, v){ const r=await fetch(window.GQL,{method:'POST',headers:{'Content-Type':'application/json'},body:JSON.stringify({query:q,variables:v||{}})}); return r.json(); }

async function loadUsers(){
    const {data} = await gql(`query{ users { id fullname email phone } }`);
    const tb=document.querySelector('#tblUsers tbody'); tb.innerHTML='';
    (data?.users??[]).forEach(u=>{
        const tr=document.createElement('tr');
        tr.innerHTML='<td>'+u.id+'</td><td>'+(u.fullname||'')+'</td><td>'+(u.email||'')+'</td><td>'+(u.phone||'')+'</td>';
        tb.appendChild(tr);
    });
}

async function createUser(){
    const input={ fullname:val('uC_fullname'), email:val('uC_email'), password:val('uC_password'), phone:val('uC_phone'), categoryIds:parseIds(val('uC_catIds')) };
    const m=`mutation($in:UserInput!){ createUser(input:$in){ id fullname email } }`;
    const r=await gql(m,{in:input});
    if(r.errors) return showMsg('uC_msg',false,r.errors[0].message);
    showMsg('uC_msg',true,r.data.createUser); loadUsers();
}
async function updateUser(){
    const id=Number(val('uU_id')); if(!id) return showMsg('uU_msg',false,'ID?');
    const input={ fullname:val('uU_fullname'), email:val('uU_email'), password:val('uU_password'), phone:val('uU_phone'), categoryIds:parseIds(val('uU_catIds')) };
    const m=`mutation($id:ID!,$in:UserInput!){ updateUser(id:$id,input:$in){ id fullname email } }`;
    const r=await gql(m,{id,in:input});
    if(r.errors) return showMsg('uU_msg',false,r.errors[0].message);
    showMsg('uU_msg',true,r.data.updateUser); loadUsers();
}
async function deleteUser(){
    const id=Number(val('uD_id')); if(!id) return showMsg('uD_msg',false,'ID?');
    const m=`mutation($id:ID!){ deleteUser(id:$id) }`;
    const r=await gql(m,{id});
    if(r.errors) return showMsg('uD_msg',false,r.errors[0].message);
    showMsg('uD_msg',true,'Deleted? '+r.data.deleteUser); loadUsers();
}
function val(id){ return document.getElementById(id).value; }