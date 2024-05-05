export AWS_ACCESS_KEY_ID=$(aws ssm get-parameters --region ap-northeast-2 --names AWS_ACCESS_KEY_ID --query Parameters[0].Value | sed 's/"//g')
export AWS_SECRET_ACCESS_KEY=$(aws ssm get-parameters --region ap-northeast-2 --names AWS_SECRET_ACCESS_KEY --query Parameters[0].Value | sed 's/"//g')
export DATASOURCE_PASSWORD=$(aws ssm get-parameters --region ap-northeast-2 --names DATASOURCE_PASSWORD --query Parameters[0].Value | sed 's/"//g')
export DATASOURCE_USERNAME=$(aws ssm get-parameters --region ap-northeast-2 --names DATASOURCE_USERNAME --query Parameters[0].Value | sed 's/"//g')
export DATASOURCE_URL=$(aws ssm get-parameters --region ap-northeast-2 --names DATASOURCE_URL --query Parameters[0].Value | sed 's/"//g')
export REDIS_URL=$(aws ssm get-parameters --region ap-northeast-2 --names REDIS_URL --query Parameters[0].Value | sed 's/"//g')

