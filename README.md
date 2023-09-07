# Java Application Repository Template

Java 애플리케이션 개발을 위해 신규 repository를 생성할 때 사용하는 template. 본 template은 다음 내용을 포함하고 있다.
- `.gitignore`
- `.editorconfig`
- dev 환경과 prd 환경 빌드를 위한 GitHub action workflow 파일
- `CODEOWNERS`

**NOTE**: IDE 또는 프로젝트 생성 도구를 통해 local 환경에서 프로젝트를 생성하면 본 템플릿에서 제공하는 `.eidtorconfig`, `.gitignore`, `README.md` 파일이 자동으로 생성될 수 있다.
이러한 자동생성 파일들은 template을 이용하여 생성한 repository와 local 환경의 소스코드를 처음 동기화를 할 때(git remote add 이후에 최초로 pull 할 때) 오류를 발생 시키므로 local 환경에서 먼저 제거하고 pull 이후에 필요에 따라 수정해야 한다.

## Prerequisite
본 template은 다음과 같은 가정을 하고 있다.

### GitHub Actions
애플리케이션 빌드를 위해 GitHub action을 사용하는 것으로 가정한다. workflow는 개발 환경에 빌드를 수행하는 `feature-build`와 운영 환경에 빌드를 수행하는 `main-build`가 있다.
feature-build는 `main` 브랜치를 제외한 branch에서 push가 되었을 때 수행된다.
main-build는 main 브랜치에 push가 되었을 때 수행된다.

### Resuable Workflows
GitHub action을 위해 작성된 workflow 파일은 `reusable workflows`를 사용하는 것으로 가정한다.
reusable workflows는 java 애플리케이션을 빌드하고 빌드 과정에서 생성된 container image tag 정보를 deploy repository에 업데이트 한다.

### GitHub Environment
GitHub repository는 `dev`와 `prd`라는 `environment` 설징이 되어 있다고 가정한다.
GitHub action에서 애플리케이션 빌드를 수행할 때 사용하는 reusable workflows는 각 환경별 빌드를 진행하는 것으로 사전 설정 되어 있다.

### Deploy Repository
본 template에 포함된 GitHub action workflows는 애플리케이션 빌드가 완료되면 배포를 위해 repository 이름 뒤에 `-deploy`의 suffix가 붙은 repository를 참조하여 빌드된 이미지의 태그정보를 업데이트 한다.
그러므로 deploy repository가 존재한다고 가정하며, workflow 파일에서는 dev 환경의 경우 deploy repository의 `values-dev.yaml` 파일, prd 환경의 경우 `values-prd.yaml` 파일에 태그 정보를 업데이트 하도록 설정 되어 있다.

### Spring boot와 build.gradle
본 template은 java 애플리케이션이 spring boot와 gradle을 사용한다고 가정한다.
그리고 Reusable workflows는 `build.gradle` 파일에 다음과 같은 `task`가 존재한다고 가정한다.
그러므로 java 프로젝트를 생성할 때는 gradle 프로젝트로 생성하고 build.gradle 파일에 다음 task를 추가한다.

```groovy
tasks.named("bootBuildImage") {
    def imageRegistry = System.getenv("IMAGE_REGISTRY")
    def imageTag = System.getenv("IMAGE_TAG")
    if (imageRegistry?.trim()) {
        if (imageTag?.trim()) {
            imageName = "${imageRegistry}/${project.name}:${imageTag}"
        } else {
            imageName = "${imageRegistry}/${project.name}:${project.version}"
        }
    }
    def dockerUsername = System.getenv("DOCKER_USERNAME")
    def dockerPassword = System.getenv("DOCKER_PASSWORD")
    if (dockerUsername?.trim() && dockerPassword?.trim()) {
        docker {
            publishRegistry {
                username = "${dockerUsername}"
                password = "${dockerPassword}"
            }
        }
    }
}

task printProjectVersion {
    println project.version
}
```

## 애플리케이션 개발 및 CI/CD를 위해 수정해야 할 것들
본 template을 이용하여 생성된 repository는 다음 내용을 확인하고 필요하다면 해당 프로젝트의 상황에 맞게 수정해야 한다.

### CODEOWNERS
CODEOWNERS는 소스코드를 관리하는 주체를 설정하는 파일이다.
본 template은 `@monolith-rnd/devops`로 설정되어 있으므로 반드시 소스코드를 관리하는 팀으로 변경해야 한다.
CODEOWNERS에 설정된 팀 또는 특정 사용자는 PR을 할 때 코드 reviewer로 자동 설정된다.

**NOTE**: `@monolith-rnd/devops`에서 devops를 본인들의 team이름으로 변경한다.

### Reusable Workflows Parameters
Reusable workflows의 `with`속성에 workflow 수행을 위한 입력값을 작성한다.
아래 기본값을 확인 후 필요하다면 해당 프로젝트의 상황에 맞게 수정해야 한다.

#### feature-build - build job
|parameter name|default value|comment|
|---|---|---|
|build-repo-name|${{ github.repository }}|빌드할 애플리케이션의 repository 이름|
|build-ref|${{ github.head_ref \|\| github.ref_name }}|빌드할 애플리케이션의 git reference(빌드 대상이 되는 브랜치)|
|java-version|17|애플리케이션을 build 할때 사용할 JDK version|
|distribution|temurin|애플리케이션을 build 할때 사용할 Java 배포판|
|is-release|false|운영 환경 배포 여부(운영 환경 배포여부에 따라 container image tag의 형식이 변경된다)|

#### feature-build - deploy-dev job
|parameter name|default value|comment|
|---|---|---|
|environment|dev|GitHub repository에 사전 정의된 environment 이름|
|deploy-repo-name|${{ github.repository }}-deploy|배포할 때 필요한 manifests가 존재하는 repository 이름|
|values-file-path|helm/values-dev.yaml|helm 배포를 위한 values 파일 경로(해당 파일에 container image tag를 업데이트 한다)|
|image-tag|${{ needs.build.outputs.image-tag }}|배포할 때 사용할 image tag|

#### main-build - deploy-prd job

**NOTE**: main-build는 feature-build의 build job과 deploy-dev job을 포함하고 있으며 동일한 parameter들을 설정해야 한다. 본 항목에서는 중복된 내용이므로 설명을 생략하지만 parameter의 변경이 필요하다면 상기 내용을 참고하여 반드시 main-build도 같이 수정해야 한다.

|parameter name|default value|comment|
|---|---|---|
|environment|prd|GitHub repository에 사전 정의된 environment 이름|
|deploy-repo-name|${{ github.repository }}-deploy|배포할 때 필요한 manifests가 존재하는 repository 이름|
|values-file-path|helm/values-prd.yaml|helm 배포를 위한 values 파일 경로(해당 파일에 container image tag를 업데이트 한다)|
|image-tag|${{ needs.build.outputs.image-tag }}|배포할 때 사용할 image tag|

## References
- [템플릿에서 리포지토리 만들기](https://docs.github.com/ko/repositories/creating-and-managing-repositories/creating-a-repository-from-a-template)
- [GitHub Actions 설명서](https://docs.github.com/ko/actions)
- [워크플로 다시 사용](https://docs.github.com/ko/actions/using-workflows/reusing-workflows)
- [배포에 환경 사용](https://docs.github.com/ko/actions/deployment/targeting-different-environments/using-environments-for-deployment)
