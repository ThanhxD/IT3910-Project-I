let {PUBLIC_GROUPS_ID, CLOSED_GROUPS_ID} = require('./fb-groups-id');
let Utils = require('./utils');

//================== MAIN =====================

const fbGroupsId = PUBLIC_GROUPS_ID;

fbGroupsId.forEach(groupId => {
    crawlFbGroup(groupId);
})

/**
 * Craw a fb group determined by passing id: posts, comments.
 * @param {String} groupId - fb group id
 */
function crawlFbGroup(groupId) {
    let fileName = groupId;
    let url = Utils.prepareUrl({
        nodeId: groupId,
        fields: "name,feed.limit(100){id}"
    });
    Utils.request(url).then(res => {
        if (!res || !res.feed) return;
        console.log('Process...', groupId, '_', res.name);
        fileName += `_${res.name}.json`;    
        let postIds = res.feed.data || [],
            postPaging = res.feed.paging || {},
            totalPostCount = 0;

        // collect other pages in pagination
        collectPagination([], postPaging, (otherPostId) => {
            postIds = postIds.concat(otherPostId);
            totalPostCount += postIds.length;

            let dataToSave = {
                name: res.name,
                id: res.id,
                total_post_count: totalPostCount,
                posts: []
            };

            // console.log('-Total posts: ', totalPostCount);
            // loop through posts
            while (postIds && postIds.length) {
                // take first post
                let post = postIds.shift();
                // console.log('Get...', post);
                if (!post.id) continue;
                let url = Utils.prepareUrl({
                    nodeId: post.id,
                    fields: "from,id,message,created_time,comments.limit(100){from,id,message,created_time,comment_count,comments.limit(100){from,id,message,created_time}}"
                });
                // request to get post's detail
                Utils.request(url).then(res => {
                    if (!res) return;
                    let commentData = (res.comments && res.comments.data) || [],
                        commentPaging = (res.comments && res.comments.paging) || {},
                        total_comment_count = 0;
                    collectPagination([], commentPaging, (otherComments) => {
                        commentData = commentData.concat(otherComments);
                        total_comment_count += commentData.length;
                        
                        // extract comments of comment
                        commentData.forEach(comment => {
                            if (comment.comments && comment.comments.data) {
                                comment.comments = comment.comments.data;
                            }
                        })

                        let postToSave = {
                            from: res.from || null,
                            id: res.id || null,
                            message: res.message || null,
                            created_time: res.created_time || null,
                            total_comment_count: total_comment_count,
                            comments: commentData || []
                        }

                        // save
                        dataToSave.posts.push(postToSave);
                        Utils.saveToFile(dataToSave, fileName);
                    })
                }).catch(err => {
                    console.log('ERROR comment: ', err.message);
                })
            }
        });
    }).catch(err => {
        console.log('Reject Promise 2: ', err.message);
    });
}

/**
 * Collect other page's data from the API's response
 * @param {String} oldOne - total data, concatenated, init as []
 * @param {Object} paging - paging field in API'response
 * @param {*} cb - callback function
 */
function collectPagination(oldOne, paging, cb) {
    if (!paging || !paging.next) {
        cb(oldOne);
        return;
    }
    Utils.request(paging.next).then(res => {
        if (!res || !res.data || !res.data.length) {
            cb(oldOne);
            return;
        }
        let data = res.data;
        if (!res.paging) {
            cb(oldOne.concat(data));
            return;
        }
        collectPagination(oldOne.concat(data), res.paging, cb);
    })
}
