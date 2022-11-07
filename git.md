# 长期保存密码
git config --global credential.helper store
# 不缓存密码
git config --global --unset credential.helper
# 禁用代理
git config --global --unset http.proxy
git config --global --unset https.proxy
# 忽略ssl认证
git config --global http.sslVerify false
git config --global https.sslVerify false
# 登陆信息
git config --global user.name "allwayup"
git config --global user.email "allwayup@163.com"
# 生成密钥对
ssh-keygen -t rsa -C "allwayup@163.com"
# 检验密钥
ssh -T git@github.com
# 当前的remote url
git remote -v
# 设置当前工程remote url
git remote add origin https://github.com/allwayup/livelife-root.git
# 切换git源
git remote set-url origin https://github.com/allwayup/livelife-root.git
