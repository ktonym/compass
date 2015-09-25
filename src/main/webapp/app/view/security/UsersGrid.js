
Ext.define("compass.view.security.UsersGrid",{
    extend: "Ext.grid.Panel",
    alias: "widget.users-grid",
    reference: "usersGrid",
    selModel: {
        mode: 'MULTI'
    },
    selType: 'checkboxmodel',
    columns: [
        {
            width: 150,
            dataIndex: "username",
            text: "Username"
        },
        {
            width: 150,
            dataIndex: "firstName",
            flex: 1,
            text: "First Name"
        },
        {
            width: 150,
            dataIndex: "lastName",
            flex: 1,
            text: "Last Name"
        },
        {
            width: 250,
            dataIndex: "email",
            text: "Email"
        },
        {
            width: 150,
            dataIndex: "idGroup",
            text: "Group"
        }
    ],
    listeners: {
//        itemclick: function(dv, record, item, index, e) {
//            this, record, item, index, e, eOpts
//            console.log(record.get('username'));
//
//        },
        select: function(dv, record, index, eOpts){
            alert(record.get('username'));
        }
    },
    bind: '{users}'
});
