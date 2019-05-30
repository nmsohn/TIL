array = ["Auckland,jan,123", "Auckland,feb,234"]
hash ||= {}
sum =0

array.each_with_index { |b, index| hash[index] = b.split(',') }

sum = hash.map{|k,v| v[2].to_i }.sum
avg = sum / hash.size

arr << [hash[0][0], sum, avg]