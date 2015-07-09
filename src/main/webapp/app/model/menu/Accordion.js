Ext.define('compass.model.menu.Accordion', {
    extend: 'Ext.data.Model',

    requires: ['compass.model.menu.TreeNode'],
    
    fields: [
        { name: 'id', mapping:'idMenu', type: 'int' },
        { name: 'text', type: 'string' },
        { name: 'iconClass', type: 'string' }

    ],

    //idProperty: 'idMenu',

    hasMany:{
        model: 'compass.model.menu.TreeNode',
        foreignKey: 'parent_id',
        name: 'items'
    }

});
