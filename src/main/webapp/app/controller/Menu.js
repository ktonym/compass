Ext.define("compass.controller.Menu",{
    extend: "Ext.app.Controller",
    stores: [
        'Menu'
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

               console.log(root.get('text'));
               console.log(root.get('iconCls'));

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

               }
               menu.getRootNode().appendChild(nodes);
               dynamicMenus.push(menu);
           });
            view.add(dynamicMenus);
            view.body.unmask();

        });


    },
    onTreePanelItemClick: function(view, record, item, index, event, options){
        console.log('TreePanelItem clicked!');
    }
});

