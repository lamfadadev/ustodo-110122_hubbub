package com.hk

class SleController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [sleInstanceList: Sle.list(params), sleInstanceTotal: Sle.count()]
    }

    def create = {
        def sleInstance = new Sle()
        sleInstance.properties = params
        return [sleInstance: sleInstance]
    }

    def save = {
        def sleInstance = new Sle(params)
        if (sleInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'sle.label', default: 'Sle'), sleInstance.id])}"
            redirect(action: "show", id: sleInstance.id)
        }
        else {
            render(view: "create", model: [sleInstance: sleInstance])
        }
    }

    def show = {
        def sleInstance = Sle.get(params.id)
        if (!sleInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'sle.label', default: 'Sle'), params.id])}"
            redirect(action: "list")
        }
        else {
            [sleInstance: sleInstance]
        }
    }

    def edit = {
        def sleInstance = Sle.get(params.id)
        if (!sleInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'sle.label', default: 'Sle'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [sleInstance: sleInstance]
        }
    }

    def update = {
        def sleInstance = Sle.get(params.id)
        if (sleInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (sleInstance.version > version) {
                    
                    sleInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'sle.label', default: 'Sle')] as Object[], "Another user has updated this Sle while you were editing")
                    render(view: "edit", model: [sleInstance: sleInstance])
                    return
                }
            }
            sleInstance.properties = params
            if (!sleInstance.hasErrors() && sleInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'sle.label', default: 'Sle'), sleInstance.id])}"
                redirect(action: "show", id: sleInstance.id)
            }
            else {
                render(view: "edit", model: [sleInstance: sleInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'sle.label', default: 'Sle'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def sleInstance = Sle.get(params.id)
        if (sleInstance) {
            try {
                sleInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'sle.label', default: 'Sle'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'sle.label', default: 'Sle'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'sle.label', default: 'Sle'), params.id])}"
            redirect(action: "list")
        }
    }
}
