
Ext.define("compass.view.security.UsersGrid",{
    extend: "Ext.grid.Panel",
    alias: "widget.users-grid",
    reference: "usersGrid",
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
    bind: '{users}'
});
