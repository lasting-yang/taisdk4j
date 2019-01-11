# taisdk4j
关于web3j的一些加强功能已移到web3jplus https://github.com/lasting-yang/web3jplus

# 编译智能合约
```
solcjs CCC.sol --bin --abi --optimize -o CCC_dir
web3j solidity generate -b CCC_dir/CCC_sol_CCC.bin -a CCC_dir/CCC_sol_CCC.abi -o src/main/java -p com.taireum.CCC
```

# 本地搭建gradle/maven私有库
```
docker pull sonatype/nexus3
docker run -d -p 8081:8081 --name nexus -v /home/dev/tmp/nexus-data:/var/nexus-data sonatype/nexus3
```

# 上传taisdk4j到本地gradle/maven私有库
```
./gradlew uploadArchives
```