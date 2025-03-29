<img height="100" alt="hyperexecute_logo" src="https://user-images.githubusercontent.com/1688653/159473714-384e60ba-d830-435e-a33f-730df3c3ebc6.png">

HyperExecute is a next-gen test execution platform by LambdaTest, designed to supercharge your test runs with unparalleled speed, scalability, and smart test management. Whether you're running cross-browser tests or executing your CI/CD pipeline, HyperExecute ensures efficiency and speed in the cloud.

HyperExecute is configured using a YAML file. Instead of moving the Hub close to you, HyperExecute brings the test scripts close to the Hub!

- [HyperExecute HomePage](https://www.lambdatest.com/hyperexecute)
- [Lambdatest HomePage](https://www.lambdatest.com)
- [HyperExecute Documentation](https://www.lambdatest.com/support/docs/getting-started-with-hyperexecute/)
- [LambdaTest Support](mailto:support@lambdatest.com)
<br />

[<img alt="Try it now" width="200px" align="center" src="images/Try it Now.svg" />](https://hyperexecute.lambdatest.com/?utm_source=github&utm_medium=repository&utm_content=java&utm_term=testng)
<br /><br />

## 📖 Table of Contents

1. [Run Your Test Using Gitpod](#-run-your-test-using-gitpod)  
2. [Run Your Test from Local Machine](#-run-your-test-from-local-machine)  
  2.1 [Fork and Clone the Repository](#1-fork-and-clone-the-repository)<br />
  2.2 [Download HyperExecute CLI](#2-download-hyperexecute-cli)<br />
  2.3 [Configure Environment Variables](#3-configure-environment-variables)<br />
  2.4 [Execute Your Test Suite](#4-execute-your-test-suite)
3. [Monitoring & Debugging](#-monitoring--debugging)
4. [Repository Structure](#-repository-structure)
5. [Key Features for QA Engineers](#-key-features-for-qa-engineers)  
  5.1 [AutoSplit Execution](#-autosplit-execution)<br />
  5.2 [Matrix Execution](#%EF%B8%8F-matrix-execution)<br />
  5.3 [Artifacts Management](#%EF%B8%8F-artifacts-management)<br />
  5.4 [Secrets Management](#-secrets-management)<br />
  5.5 [Pre-Steps and Dependency Caching](#pre-steps-and-dependency-caching)<br />
  5.6 [Post-Steps](#post-steps)
6. [Documentation & Resources](#-documentation--resources)
7. [Contributing](#-contributing)
8. [Contact & Support](#-contact--support)

## 🚀 Run your test using Gitpod
Follow the below steps to run Gitpod button:

1. Click '**Open in Gitpod**' button (You will be redirected to Login/Signup page).
2. Login with Lambdatest credentials and it will be redirected to Gitpod editor in new tab and current tab will show hyperexecute dashboard.

[<img alt="Run in Gitpod" width="200px" align="center" src="images/Gitpod.svg" />](https://hyperexecute.lambdatest.com/hyperexecute/jobs?type=gitpod&framework=testng)
<br /><br />

## 🚀 Run your test from Local Machine
> Before proceeding forward, make sure you have created your account on [LambdaTest](https://accounts.lambdatest.com/login)

Follow the steps below to get started with running Selenium TestNG tests on HyperExecute!
<br />

### 1. Fork and Clone the Repository
First, fork this repository and make a copy of it in your github account, and then clone this repository to your local machine:

```bash
git clone https://github.com/LambdaTest/testng-selenium-hyperexecute-sample.git
cd testng-selenium-hyperexecute-sample
```
***

### 2. Download HyperExecute CLI
It is used for interacting and running the tests on the HyperExecute. The [CLI](https://www.lambdatest.com/support/docs/hyperexecute-cli-run-tests-on-hyperexecute-grid/) provides a host of other useful features that accelerate test execution. In order to trigger tests using the CLI, you need to download the HyperExecute CLI binary corresponding to the platform (or OS) from where the tests are triggered:

> It is recommended to download the binary in the project's parent directory.

| Operating System | HyperExecute CLI Download Link |
|------------------|--------------------------------|
| Linux | https://downloads.lambdatest.com/hyperexecute/linux/hyperexecute |
| Windows | https://downloads.lambdatest.com/hyperexecute/windows/hyperexecute.exe |
| macOS | https://downloads.lambdatest.com/hyperexecute/darwin/hyperexecute |

***

### 3. Configure Environment Variables
Before the tests are run, please [set the environment variables](https://www.lambdatest.com/support/docs/hyperexecute-environment-variable-setup/) `LT_USERNAME` and `LT_ACCESS_KEY` from the terminal. The account details are available on your [LambdaTest Profile](https://accounts.lambdatest.com/detail/profile) page.

- **Linux / macOS :**
  ```bash
  export LT_USERNAME=YOUR_LAMBDATEST_USERNAME
  export LT_ACCESS_KEY=YOUR_LAMBDATEST_ACCESS_KEY
  ```

- **Windows :**
  ```bash
  set LT_USERNAME=YOUR_LAMBDATEST_USERNAME
  set LT_ACCESS_KEY=YOUR_LAMBDATEST_ACCESS_KEY
  ```
***

### 4. Execute your Test Suite
> **NOTE :** In case of MacOS, if you get a permission denied warning while executing CLI, simply run `chmod u+x ./hyperexecute` to allow permission. In case you get a security popup, allow it from your **System Preferences** → **Security & Privacy** → **General tab**.

Run the below command in your terminal at the root folder of the project:

```bash
./hyperexecute --config RELATIVE_PATH_OF_YOUR_YAML_FILE
```

OR use this command if you have not exported your username and access key in the above step.

```bash
./hyperexecute --user YOUR_LAMBDATEST_USERNAME --key YOUR_LAMBDATEST_ACCESS_KEY --config RELATIVE_PATH_OF_YOUR_YAML_FILE
```
***

## 🔍 Monitoring & Debugging
You can monitor your test execution in real-time using the HyperExecute Automation Dashboard. Easily navigate between test logs, Selenium reports, and HyperExecute logs.

🔗 Visit the [HyperExecute Dashboard](https://hyperexecute.lambdatest.com/hyperexecute)

## 🧑‍💻 Repository Structure
Here's a breakdown of the repository structure:

```bash
/src/test/java/
              Test1.java # Example Selenium TestNG test case
              Test2.java # Example Selenium TestNG test case
              Test3.java # Example Selenium TestNG test case

/xml/
    testng_linux.xml # XML file for linux OS - defining parameters- browser, browser version, platform, platform (OS), resolution
    testng_mac.xml # XML file for macOS - defining parameters- browser, browser version, platform, platform (OS), resolution
    testng_win.xml # XML file for Win10 OS - defining parameters- browser, browser version, platform, platform (OS), resolution
    testng_win11.xml # XML file for Win11 OS - defining parameters- browser, browser version, platform, platform (OS), resolution

/yaml
  /linux
    /v1/
      testng_hyperexecute_autosplit_sample.yaml  # YAML 0.1 file for Linux OS using AutoSplit Method
      testng_hyperexecute_matrix_sample.yaml     # YAML file for Linux OS using Matrix Method
    /v2/
      testNG_hyperexecute_autosplit_sample.yaml  # YAML 0.2 file for Linux OS using AutoSplit Method
  /mac/
    /v1/
      testng_hyperexecute_autosplit_sample.yaml  # YAML 0.1 file for macOS using AutoSplit Method
      testng_hyperexecute_matrix_sample.yaml     # YAML file for macOS using Matrix Method
    /v2/
      testng_hyperexecute_autosplit_sample.yaml  # YAML 0.2 file for macOS using AutoSplit Method
  /win/
    /v1/
      testng_hyperexecute_autosplit_sample.yaml  # YAML 0.1 file for Win10 OS using AutoSplit Method
      testng_hyperexecute_matrix_sample.yaml     # YAML file for Win10 OS using Matrix Method
    /v2/
      testng_hyperexecute_autosplit_sample.yaml  # YAML 0.2 file for Win10 OS using AutoSplit Method
  /win11/
    /v1/
      testng_hyperexecute_autosplit_sample.yaml  # YAML 0.1 file for Win11 OS using AutoSplit Method
      testng_hyperexecute_matrix_sample.yaml     # YAML file for Win11 OS using Matrix Method
    /v2/
      testng_hyperexecute_autosplit_sample.yaml  # YAML 0.2 file for Win11 OS using AutoSplit Method
  /testng_hyperexecute_hybrid_sample.yaml        # YAML file for Hybrid Method

pom.xml                   # Project Object Model (POM) for Maven
```
- **Test.java :** Contains a sample test case using TestNG and Selenium WebDriver.
- **testng_hyperexecute_autosplit_sample.yml :** HyperExecute YAML file for executing test using [AutoSplit Method](https://www.lambdatest.com/support/docs/hyperexecute-auto-split-strategy/).
- **testng_hyperexecute_matrix_sample.yml :** HyperExecute YAML file for executing test using [Matrix Method](https://www.lambdatest.com/support/docs/hyperexecute-matrix-multiplexing-strategy/).
- **testng_hyperexecute_hybrid_sample.yml :** HyperExecute YAML file for executing test using [Hybrid Method](https://www.lambdatest.com/support/docs/hyperexecute-hybrid-strategy/).
- **pom.xml :** Maven configuration file that includes dependencies for Selenium, TestNG, and WebDriver.

***

## 🧑‍💻 Key Features for QA Engineers
### 🏃 AutoSplit Execution
[AutoSplit](https://www.lambdatest.com/support/docs/hyperexecute-auto-split-strategy/) execution helps to distribute your test load efficiently, optimizing test execution time. You can configure concurrency at various levels like test suite, file, or module.

```yaml
autosplit: true
concurrency: 4
```

This will run tests with 4 parallel sessions, improving test speed.

### 🗂️ Matrix Execution
[Matrix](https://www.lambdatest.com/support/docs/hyperexecute-matrix-multiplexing-strategy/) execution helps you to the same tests across different configurations (like different browsers, OS, etc.) using Matrix Execution. This is useful for cross-environment compatibility testing.

```yaml
matrix:
  classname: ["Test_1", "Test_2", "Test_3"]
```
### ⚙️ Artifacts Management
HyperExecute makes it easy to upload and download test [artifacts](https://www.lambdatest.com/support/docs/hyperexecute-artifacts/) (like logs, reports, etc.). Artifacts help you analyze the results of your test runs, improving debugging.

```yaml
uploadArtifacts:
  - name: Final Report
    path:
      - target/site/**
  - name: Surefire Report
    path:
      - target/surefire-reports/**
```
### 🔐 Secrets Management
Need to use sensitive information like API keys? HyperExecute allows you to store and manage secrets securely.

```yaml
env:
  PAT: ${{ .secrets.testKey }}
```

### Pre-Steps and Dependency Caching
To save time on repeated test runs, you can cache dependencies and set up pre-steps. For example:

```yaml
cacheKey: '{{ checksum "pom.xml" }}'
cacheDirectories:
  - $CACHE_DIR

pre:
  - mvn clean install
```
This ensures dependencies like Maven packages are cached and not downloaded every time you run tests.

### Post-Steps
After your tests complete, you may want to clean up or generate final reports. Post-steps help you do that:

```yaml
post:
  - cat yaml/linux/v1/testng_hyperexecute_autosplit_sample.yaml
```

## 📚 Documentation & Resources
For more details on how to run and customize tests with HyperExecute, check out:

- [HyperExecute Documentation](https://www.lambdatest.com/support/docs/getting-started-with-hyperexecute/)
- [HyperExcute API Documentation](https://www.lambdatest.com/support/api-doc/?key=hyperexecute)
- [HyperExecute Case Study Blogs](https://www.lambdatest.com/blog/category/hyperexecute/)

## 🤝 Contributing
We welcome contributions to improve this repository. Here's how you can contribute:

- Fork this repository.
- Create a new branch -> `git checkout -b YOUR_NAME/FIX_NAME`.
- Make your changes and commit them -> `git commit -m 'YOUR_COMMIT_MESSAGE'`.
- Push the branch to your fork -> `git push origin YOUR_NAME/FIX_NAME`.
- Open a Pull Request to the main repository.

## 📞 Contact & Support
Need help or have questions? Reach out to us:

- Email: support@lambdatest.com
- Visit our [Community Forum](https://community.lambdatest.com/)


Thank you for using HyperExecute! 🚀 Happy Testing!
