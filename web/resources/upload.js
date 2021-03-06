function checkImageType(fileName) {

    var pattern = /jpg|gif|png|jpeg/i;

    return fileName.match(pattern);
}

function getFileInfo(fullName) {
    var fileName, imgsrc, getLink, fileLink;

    if(checkImageType(fullName)) {
        var front = fullName.substr(0, 12);
        var end = fullName.substr(14);
        imgsrc = "/displayFile?fileName=" + fullName;
        fileLink = fullName.substr(14);
        getLink = "/displayFile?fileName=" + front + end;
    } else {
        imgsrc = "/resources/dist/img/file.jpeg";
        fileLink = fullName.substr(12);
        getLink = "/displayFile?fileName=" + fullName;
        console.log("********************************");
        console.log(fullName);
        console.log("********************************");
    }

    fileName = fileLink.substr(fileLink.indexOf("_") + 1);

    return {
        fileName: fileName,
        imgsrc: imgsrc,
        getLink: getLink,
        fullName: fullName
    };
}
