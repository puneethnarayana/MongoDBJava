use agg
db.products.aggregate([
    {$group:
     {
	 _id:"$manufacturer", 
	 num_products:{$sum:1}
     }
    }
])

//To find the authors with maximum comments
db.posts.aggregate([{$unwind:"$comments"},{$group:{_id:"$comments.author",no_comments:{$sum:1}}},{$sort:{no_comments:1}}])

db.posts.aggregate([{$unwind:"$comments.author"},{$group:{_id:"$comments.author",no_comments:{$sum:1}}},{$sort:{no_comments:1,_id:1}},{$limit:1}])

db.grades.aggregate([{$unwind:"$scores"},{$group:{_id:"$class_id",avg_score:{$avg:"$scores.score"}}},{$sort:{avg_score:1}}])

db.zips.aggregate([{$project: {first_char: {$substr : ["$city",0,1]},}},{$match:{first_char="1"}},{$group:{_id:"$city"}}])

db.zips.aggregate([{$project: {first_char: {$substr : ["$city",0,1]},}},{$match:{_id:{first_char:"2"}}}])

db.zips.aggregate([{$project:{first_char:{$substr:["$city",0,1]},pop:"$pop"}},
{$match:{first_char:/^[0-9]*$/}},{$group:{_id:"Sum Total of Rural",Total:{$sum:"$pop"}}}])