const fs = require('fs');
const http = require('http');
const cheerio = require('cheerio');

function request(url) {
    return new Promise((resolve, reject) => {
        http.get(url, res => {
            let data = '';
            res.on('data', chunk => {
                data += chunk;
            });
            res.on('end', () => {
                resolve(data.toString());
            });
        }).on('error', err => {
            reject(err.message);
        })
    });
}
request('http://www.phuot.vn/forums/79-Tour').then(response => {
    const $ = cheerio.load(response);
    let aTags = $('#threads .inner .threadtitle a');

    // links on current thread
    let links = Object.keys(aTags)
                        .map(key => aTags[key] && aTags[key].attribs && aTags[key].attribs.href)
                        .filter(item => !!item);
    
    // request every link to get content
    // links.forEach(link => {
    //     let url = 'http://www.phuot.vn/' + link;
    //     request(url).then(response => {
    //         const $$ = cheerio.load(response);
    //         try {
    //             let owner = $$('strong span')[0].children[0].data || $$('strong span strike')[0].children[0].data;
    //             let header = $$('.postbody .postrow .posttitle')[0].children[2].data || '';
    //             let contentTag = $$('.content blockquote')[0] || null;
    //             let contentChilds = contentTag && contentTag.children;
    //             let contentText = contentChild && contentChilds.map(child => {
    //                 if (child.type === 'text') {
    //                     return child.data;
    //                 } else {
    //                     return '';
    //                 }
    //             })
    //         } catch(err) {
    //             console.log('ERROR: '+ url);
    //             console.log(err.message);
    //         }

    //         let postToSave = {
    //             owner: owner,
    //             header: header,
    //             content: contentText
    //         }
    //         fs.writeFile('phuot.txt', JSON.stringify(postToSave, null, 2), () => {
    //             console.log('write done');
    //         })
    //     })
    // })

    // // test
    request('http://www.phuot.vn/threads/347669-Tour-gi%C3%A1-r%E1%BA%BB-v%C3%A0-%C4%90%E1%BA%B7t-Ph%C3%B2ng-kh%C3%A1ch-s%E1%BA%A1n-tr%C3%AAn-to%C3%A0n-qu%E1%BB%91c')
        .then(response => {
            const $$ = cheerio.load(response);
            let owner = $$('strong span strike')[0].children[0].data;
            let header = $$('.postbody .postrow .posttitle')[0].children[2].data;
            let contentTag = $$('.content blockquote')[0];
            let contentChilds = contentTag.children;
            let contentText = contentChilds.map(child => {
                if (child.type === 'text') {
                    return child.data;
                } else {
                    return '';
                }
            })

            let postToSave = {
                owner: owner,
                header: header,
                content: contentText
            }
            fs.writeFile('1.txt', JSON.stringify(postToSave, null, 2), () => {
                console.log('write done');
            })
        })

    request('http://www.phuot.vn/threads/348821-Tour-kh%C3%A1m-ph%C3%A1-shangri-la-t%E1%BA%A1i-Ti%C3%AAn-Phong-Travel')
        .then(response => {
            // fs.writeFile('hello.txt', response, () => {
            //     console.log('write done');
            // })
            const $$ = cheerio.load(response);
            let owner = $$('strong span')[0].children[0].data;
            let header = $$('.postbody .postrow .posttitle')[0].children[2].data;
            let contentTag = $$('.content blockquote')[0];
            let contentChilds = contentTag.children;
            let contentText = contentChilds.map(child => {
                if (child.type === 'text') {
                    return child.data;
                } else {
                    return '';
                }
            })

            let postToSave = {
                owner: owner,
                header: header,
                content: contentText
            }
            fs.appendFile('2.txt', JSON.stringify(postToSave, null, 2), () => {
                console.log('write done');
            })
        })
});