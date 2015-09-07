Ext.define("compass.controller.Menu",{
    extend: "Ext.app.Controller",
    stores: [
        'Menu'
    ],

    refs: [
        {
            ref: 'mainPanel',
            selector: 'mainpanel'
        }
    ],

    init: function(application){

        this.control({

            "menutree": {
                itemclick: this.onTreePanelItemClick
            },
            "mainmenu" : {
                render: this.renderDynamicMenu
            }

        });

    },
    renderDynamicMenu: function(view,options){
        console.log('Dynamic menu rendered');
        //alert('menu rendered');
        var dynamicMenus = [];

        view.body.mask('Loading Menus... Please wait...');

        this.getMenuStore().load(function(records, op, success){

           //console.log(this);

           Ext.each(records, function(root){

               var menu = Ext.create('compass.view.menu.Tree',{
                   title: translations[root.get('text')],
                   iconCls: root.get('iconCls')
               });


               var treeNodeStore = root.items(),
                   nodes = [],
                   item;

               for (var i=0; i<treeNodeStore.getCount(); i++){
                   item = treeNodeStore.getAt(i);

                   nodes.push({
                       text: translations[item.get('text')],
                       leaf: true,
                       glyph: item.get('iconCls'),
                       id: item.get('id'),
                       className: item.get('className')
                   });

                   console.log(item.get('iconCls'));

               }
               menu.getRootNode().appendChild(nodes);
               dynamicMenus.push(menu);
           });
            view.add(dynamicMenus);
            view.body.unmask();

        });


    },
    onTreePanelItemClick: function(view, record, item, index, event, options){

        var mainPanel = this.getMainPanel();

        var newTab = mainPanel.items.findBy(
           function(tab){
                return tab.title === record.get('text');
        });
        console.log(record.get('glyph'));
        console.log(record.get('iconCls'));

        if(!newTab){
            newTab = mainPanel.add({
                    xtype: record.get('className'),
                    closable: true, // #7
                    glyph: record.get('glyph'), // #8
                    title: record.get('text')
                });
        }

        mainPanel.setActiveTab(newTab);

    }
});

