module.exports = function(app){
    require('./requestAttachmentDrtv')(app);
    require('./requestAttachmentDrtvCtrl')(app);
}