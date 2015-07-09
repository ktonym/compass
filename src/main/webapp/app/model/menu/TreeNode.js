Ext.define('compass.model.menu.TreeNode', {
    extend: 'Ext.data.Model',
    
    fields: [
        { name: 'id', type: 'int', mapping: 'idMenu' },
        { name: 'text' },
        { name: 'iconClass' },
        { name: 'className' },
        { name: 'parent_id', mapping:'idParentMenu' }

    ]
});
