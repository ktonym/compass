Ext.define("compass.controller.Menu",{
    extend: "Ext.app.Controller",
    init: function(application){

        this.control({

            "menutree": {
                itemclick: this.onTreePanelItemClick
            },
            "mainmenu" : {
                render: this.renderDynamicMenu
            }

        });

    }
});

