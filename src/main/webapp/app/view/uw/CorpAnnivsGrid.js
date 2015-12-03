Ext.define('compass.view.uw.CorpAnnivsGrid',{

    extend: 'Ext.grid.Panel',
    xtype: 'corpannivs-grid',
    reference: 'corpAnnivsGrid',
    requires: ['compass.util.Glyphs'],
    bind : '{corporatesGrid.selection.annivs}',
    title: 'Anniversaries',
    glyph: compass.util.Glyphs.getGlyph('cover'),
    border: true,

    //bind: '{corpannivs}',
//    plugins:
//    {
//        ptype: 'subtable',
//        association: 'intermediary',
//        headerWidth: 24,
//        columns: [
//            {
//                text: 'Type',
//                dataIndex: 'type',
//                width: 100
//            },
//            {
//                text: 'Name',
//                dataIndex: 'intermediaryName',
//                width: 240
//            }
//        ]
//    },
    columns: [
        {
            text: 'Id',
            dataIndex: 'idCorpAnniv'
        },
        {
            text: 'Anniversary',
            dataIndex: 'anniv'
        },
        {
            text: 'Inception',
            dataIndex: 'inception'
        },
        {
            text: 'Expiry',
            dataIndex: 'expiry'
        },
        {
            text: 'Renewal',
            dataIndex: 'renewalDate'
        },
        {
            dataIndex: 'idIntermediary',
            renderer: function(value, metaData, record ){
                var intermediaryStore = Ext.getStore('staticData.Intermediaries');
                var intermediary = intermediaryStore.findRecord('idIntermediary', value);
                return intermediary != null ? intermediary.get('intermediaryName') : value;
            }
        }
    ]
})