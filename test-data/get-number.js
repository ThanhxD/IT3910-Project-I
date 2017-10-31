const fs = require('fs');

// read json file
let obj = JSON.parse(fs.readFileSync('./test-data/878078555563309_CLB HƯỚNG DẪN VIÊN DU LỊCH  VIỆT NAM.json', 'utf8'));
// get an array of comments
let gComments = [];
obj.posts.forEach(post => {
    let comments = post.comments;
    if (comments.length) {
        comments.forEach(comment => {
            gComments.push(comment.message);
            if(comment.comments && comment.comments.length) {
                let subComments = comment.comments;
                subComments.forEach(sComment => {
                    gComments.push(sComment.message);
                })
            }
        })
    }
})
// get an array of phone numbers
const PHONE_NUMBER_REGEXP = /(0|84)(([89]\d{8})|([12]\d{9}))/gm;
let gNumbers = [];
gComments.forEach(cmt => {
    let reducedStr = cmt.replace(/(http|https)?(:\/\/)(www)?[^ \r\n]+/gm, '*link*') // remove urls
                        .replace(/[ \.\+\-\(\)]/g, '');                             //remove linking-letters

    // match number
    let numbers = reducedStr.match(PHONE_NUMBER_REGEXP);
    if (numbers && numbers.length) {
        gNumbers.push(...numbers);
    }
})
// remove duplicated numbers
let real = gNumbers.filter((n, p, s) => s.indexOf(n) === p);

// write out to file
fs.writeFile('comments.txt', gComments.join('\n'), () => {});
fs.writeFile('number.txt', real.join('\n'), () => {});
